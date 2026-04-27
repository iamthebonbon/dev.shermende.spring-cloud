## access to resources: configmaps. 403 issue

https://stackoverflow.com/questions/55498702/how-to-fix-forbiddenconfigured-service-account-doesnt-have-access-with-spark

### configure namespaces

##### run cluster-role.yml before

```
kubectl create namespace env-kafka
kubectl create serviceaccount env-kafka-sa -n env-kafka
kubectl create clusterrolebinding env-kafka-rb --clusterrole=microservices-kubernetes-namespace-reader --serviceaccount=env-kafka:env-kafkfa-sa
```

```
kubectl create namespace env-postgres
kubectl create serviceaccount env-postgres-sa -n env-postgres
kubectl create clusterrolebinding env-postgres-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=env-postgres:env-postgres-sa
```

```
kubectl create namespace env-authorization
kubectl create serviceaccount env-authorization-sa -n env-authorization
kubectl create clusterrolebinding env-authorization-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=env-authorization:env-authorization-sa
```
```
kubectl create namespace app-reference
kubectl create serviceaccount app-reference-sa -n app-reference 
kubectl create clusterrolebinding app-reference-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=app-reference:app-reference-sa
```
```
kubectl create namespace app-game
kubectl create serviceaccount app-game-sa -n app-game
kubectl create clusterrolebinding app-game-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=app-game:app-game-sa
```
```
kubectl create namespace env-sba
kubectl create serviceaccount env-sba-sa -n env-sba 
kubectl create clusterrolebinding env-sba-rb \
--clusterrole=microservices-kubernetes-namespace-reader \
--serviceaccount=env-sba:env-sba-sa
```

###### # remove none images

docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
