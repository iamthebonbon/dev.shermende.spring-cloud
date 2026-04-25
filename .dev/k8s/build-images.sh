#!/bin/bash
mvn clean package -DskipTests
docker build -f env-authorization/src/main/docker/Dockerfile -t env-authorization:0.0.1 ./env-authorization
docker build -f app-reference/src/main/docker/Dockerfile -t app-reference:0.0.1 ./app-reference
docker build -f app-game/src/main/docker/Dockerfile -t app-game:0.0.1 ./app-game
docker build -f env-sba/src/main/docker/Dockerfile -t env-sba:0.0.1 ./env-sba

kubectl apply -f .dev/k8s/cluster-role.yml

kubectl create namespace env-kafka
kubectl create serviceaccount env-kafka-sa -n env-kafka
kubectl create clusterrolebinding env-kafka-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-kafka:env-kafkfa-sa

kubectl create namespace env-postgres
kubectl create serviceaccount env-postgres-sa -n env-postgres
kubectl create clusterrolebinding env-postgres-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=env-postgres:env-postgres-sa

kubectl create namespace env-authorization
kubectl create serviceaccount env-authorization-sa -n env-authorization
kubectl create clusterrolebinding env-authorization-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-authorization:env-authorization-sa

kubectl create namespace app-reference
kubectl create serviceaccount app-reference-sa -n app-reference
kubectl create clusterrolebinding app-reference-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=app-reference:app-reference-sa

kubectl create namespace app-game
kubectl create serviceaccount app-game-sa -n app-game
kubectl create clusterrolebinding app-game-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=app-game:app-game-sa

kubectl create namespace env-sba
kubectl create serviceaccount env-sba-sa -n env-sba
kubectl create clusterrolebinding env-sba-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=env-sba:env-sba-sa
