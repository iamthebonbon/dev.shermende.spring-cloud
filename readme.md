# Spring cloud complex application

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=shermende_dev.shermende.spring-cloud&metric=alert_status)](https://sonarcloud.io/dashboard?id=shermende_dev.shermende.spring-cloud)

![Maven pipeline (release-hotfix)](https://github.com/shermende/dev.shermende.spring-cloud/workflows/Maven%20pipeline%20(release-hotfix)/badge.svg)

![Maven pipeline (feature-bugfix)](https://github.com/shermende/dev.shermende.spring-cloud/workflows/Maven%20pipeline%20(feature-bugfix)/badge.svg)

```bash
# run jwt
$ docker-compose -f docker-compose.yml -f docker-compose.jwt.yml up --build -d
```

```bash
# run oauth
$ docker-compose -f docker-compose.yml -f docker-compose.oauth.yml up --build -d
```

```bash
# run jwt with metrics to influx
$ docker-compose -f docker-compose.yml -f docker-compose.metrics.yml -f docker-compose.jwt.yml up --build -d
```

```bash
# run jwt with EFK-log 
$ docker-compose -f docker-compose.yml -f docker-compose.logback.yml -f docker-compose.jwt.yml up --build -d
```

```bash
# run jwt with EFK-log and metrics to influx 
$ docker-compose -f docker-compose.yml -f docker-compose.logback.yml -f docker-compose.metrics.yml -f docker-compose.jwt.yml up --build -d
```

#### JWT keygen

```bash
keytool -genkey -alias env-authorization -keystore ~/env-authorization.p12 -storetype PKCS12 -keyalg RSA -keysize 4096
openssl pkcs12 -info -in ~/env-authorization.p12 -nodes 
```