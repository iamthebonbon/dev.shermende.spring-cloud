#!/bin/bash
set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(cd "$SCRIPT_DIR/../.." && pwd)"

echo "=== Step 1: Build Maven project ==="
cd "$PROJECT_DIR"
mvn clean package -DskipTests

echo "=== Step 2: Build Docker images ==="
docker build -f env-authorization/src/main/docker/Dockerfile -t env-authorization:0.0.1 ./env-authorization
docker build -f app-reference/src/main/docker/Dockerfile -t app-reference:0.0.1 ./app-reference
docker build -f app-game/src/main/docker/Dockerfile -t app-game:0.0.1 ./app-game

echo "=== Step 3: Apply RBAC ==="
kubectl apply -f "$SCRIPT_DIR/cluster-role.yml"

echo "=== Step 4: Create namespaces and service accounts ==="
kubectl create namespace env-kafka --dry-run=client -o yaml | kubectl apply -f -
kubectl create serviceaccount env-kafka-sa -n env-kafka --dry-run=client -o yaml | kubectl apply -f -
kubectl create clusterrolebinding env-kafka-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-kafka:env-kafka-sa --dry-run=client -o yaml | kubectl apply -f -

kubectl create namespace env-postgres --dry-run=client -o yaml | kubectl apply -f -
kubectl create serviceaccount env-postgres-sa -n env-postgres --dry-run=client -o yaml | kubectl apply -f -
kubectl create clusterrolebinding env-postgres-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-postgres:env-postgres-sa --dry-run=client -o yaml | kubectl apply -f -

kubectl create namespace env-authorization --dry-run=client -o yaml | kubectl apply -f -
kubectl create serviceaccount env-authorization-sa -n env-authorization --dry-run=client -o yaml | kubectl apply -f -
kubectl create clusterrolebinding env-authorization-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-authorization:env-authorization-sa --dry-run=client -o yaml | kubectl apply -f -

kubectl create namespace app-reference --dry-run=client -o yaml | kubectl apply -f -
kubectl create serviceaccount app-reference-sa -n app-reference --dry-run=client -o yaml | kubectl apply -f -
kubectl create clusterrolebinding app-reference-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=app-reference:app-reference-sa --dry-run=client -o yaml | kubectl apply -f -

kubectl create namespace app-game --dry-run=client -o yaml | kubectl apply -f -
kubectl create serviceaccount app-game-sa -n app-game --dry-run=client -o yaml | kubectl apply -f -
kubectl create clusterrolebinding app-game-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=app-game:app-game-sa --dry-run=client -o yaml | kubectl apply -f -

echo "=== Step 5: Create JWT keystore secret ==="
kubectl create secret generic jwt-keystore \
  --from-file=jwt.jks="$SCRIPT_DIR/../../.dev/docker-compose/examples/jwt.jks" \
  -n env-authorization --dry-run=client -o yaml | kubectl apply -f -

echo "=== Step 6: Deploy infrastructure (Zookeeper, Kafka, PostgreSQL) ==="
kubectl apply -f "$SCRIPT_DIR/kafka/deployment-zookeeper.yml"
kubectl apply -f "$SCRIPT_DIR/kafka/deployment-kafka.yml"
kubectl apply -f "$SCRIPT_DIR/deployment-postgres-game.yml"
kubectl apply -f "$SCRIPT_DIR/deployment-postgres-reference.yml"

echo "Waiting for PostgreSQL pods to be ready..."
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=env-postgres-game -n env-postgres --timeout=120s
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=env-postgres-reference -n env-postgres --timeout=120s

echo "=== Step 7: Deploy env-authorization ==="
kubectl apply -f "$SCRIPT_DIR/deployment-env-authorization.yml"
echo "Waiting for env-authorization to be ready..."
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=env-authorization -n env-authorization --timeout=120s

echo "=== Step 8: Deploy app-reference ==="
kubectl apply -f "$SCRIPT_DIR/deployment-app-reference.yml"
echo "Waiting for app-reference to be ready..."
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=app-reference -n app-reference --timeout=120s

echo "=== Step 9: Deploy app-game ==="
kubectl apply -f "$SCRIPT_DIR/deployment-app-game.yml"
echo "Waiting for app-game to be ready..."
kubectl wait --for=condition=ready pod -l app.kubernetes.io/name=app-game -n app-game --timeout=120s

echo ""
echo "=== Deployment complete! ==="
echo ""
echo "Check status:"
echo "  kubectl get pods -n env-kafka"
echo "  kubectl get pods -n env-postgres"
echo "  kubectl get pods -n env-authorization"
echo "  kubectl get pods -n app-reference"
echo "  kubectl get pods -n app-game"
echo ""
echo "Port-forward app-game:"
echo "  kubectl port-forward svc/app-game 8200:8200 -n app-game"
