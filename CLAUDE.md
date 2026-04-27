# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build entire project (from root)
mvn clean install

# Build a single module
mvn clean install -pl app-game -am

# Run a single service locally (e.g., app-game)
mvn spring-boot:run -pl app-game

# Run tests for a single module
mvn test -pl app-game

# Run a single test class
mvn test -pl app-game -Dtest=SomeTestClass

# Run a single test method
mvn test -pl app-game -Dtest=SomeTestClass#testMethod
```

**Java 8** is required. Spring Boot 2.3.7.RELEASE with Spring Cloud Hoxton.SR9.

## Docker Compose (from `.dev/docker-compose/`)

```bash
# JWT auth mode
docker-compose -f docker-compose.yml -f docker-compose.jwt.yml up --build -d

# OAuth2 opaque token mode
docker-compose -f docker-compose.yml -f docker-compose.oauth.yml up --build -d

# With metrics (InfluxDB + Chronograf)
docker-compose -f docker-compose.yml -f docker-compose.metrics.yml -f docker-compose.jwt.yml up --build -d

# With EFK logging stack
docker-compose -f docker-compose.yml -f docker-compose.logback.yml -f docker-compose.jwt.yml up --build -d
```

## Architecture

This is a Spring Cloud microservices platform with 10 Maven modules.

### Infrastructure Services (`env-*`)

| Service | Port | Management Port | Purpose |
|---------|------|-----------------|---------|
| env-eureka | 8761 | 7761 | Service discovery (Netflix Eureka) |
| env-configuration | dynamic | - | Centralized config (Spring Cloud Config) |
| env-authorization | 8000/8082 | - | OAuth2 authorization server (JWT or opaque tokens) |
| env-zuul | 8080 | - | API gateway (Netflix Zuul) |
| env-sba | 8081 | - | Spring Boot Admin monitoring UI |

### Business Services (`app-*`)

| Service | Port | Management Port | Purpose |
|---------|------|-----------------|---------|
| app-reference | 8100 | 7100 | Reference data (points, reasons, scenarios) |
| app-game | 8200 | 7200 | Game logic, consumes app-reference via Feign |
| app-reference-api | - | - | Shared API contracts/DTOs (library, not runnable) |

### Libraries (`lib-*`)

| Module | Purpose |
|--------|---------|
| lib-dal | Data access layer: Spring Data JPA + QueryDSL base classes |
| lib-security | OAuth2 resource server config (JWT and opaque token profiles) |

### Inter-Service Communication

- **app-game** calls **app-reference** via OpenFeign clients defined in `app-reference-api`
- Two Feign profiles: **global** (Eureka discovery-based) and **local** (direct `http://127.0.0.1:8100`)
- Security context propagated through Feign via `hystrix.shareSecurityContext: true`
- Kafka used for async messaging between services

### Authentication Profiles

Two mutually exclusive security modes, selected via Spring profiles and docker-compose overlays:
- **JWT**: JWKS endpoint at `/.well-known/jwks.json`, keystore in `examples/jwt.jks`
- **OAuth2 opaque**: Token introspection at `/oauth/check_token`

Both configured in `lib-security` via `JwtResourceServerConfiguration` and `OpaqueResourceServerConfiguration`.

### Databases

- Default: H2 in-memory (`jdbc:h2:mem:{service-name}`)
- Production: PostgreSQL (configured via docker-compose or K8s secrets)
- Flyway for schema migrations

### Observability

- Micrometer metrics exported to InfluxDB (via `metrics` profile)
- Spring Cloud Sleuth + Zipkin for distributed tracing
- Logback with Kafka appender for centralized logging (EFK stack)
- Spring Boot Admin for service health monitoring

### CI/CD

- GitHub Actions: `maven-feature-bugfix.yml` and `maven-release-hotfix.yml`
- SonarCloud for code quality analysis
- JaCoCo for code coverage

### Kubernetes

K8s manifests in `.dev/k8s/`. Each service runs in its own namespace with dedicated service accounts. Config reloading enabled via `kubernetes.reload.enabled: true`, secrets mounted at `/etc/secret`.

### Code Generation

QueryDSL Q-classes are generated via `apt-maven-plugin` during build. Lombok is used project-wide (`lombok.config` at root).