# DCL-355: MicroServices Patterns with Examples in Spring Boot

This repository contains the code, exercises, and reference projects from the **DCL-355: Microservices Patterns with Examples in Spring Boot** training. It demonstrates service discovery, centralized configuration, reactive gatewaying, transactional messaging (outbox, DLT), data consistency (CQRS), resiliency (Resilience4j), security (Keycloak/OIDC), observability (Actuator/JFR/JMX), and deployment practices using Spring Boot and Spring Cloud. 

---

## Repository structure

The top-level directories are independent Spring Boot apps or focused labs. Open any folder directly in your IDE to run it.

- **config-server** — Centralized configuration server for the fleet of services.  
- **registry-service** — Service discovery/registry (client registration and lookup).  
- **reactive-gateway** — Reactive API Gateway with route composition and filters.  
- **crm-reactive-microservice**, **crm-reactive-consumer-service**, **crm-write-model-microservice**, **crm-read-model-microservice** — CRM domain showcasing reactive pipelines and **CQRS** (write/read segregation).  
- **order-management-microservice**, **payment-microservice**, **inventory-microservice** — Sample domain services for orchestration/saga-like flows and integrations.  
- **resiliency-patterns** — Resilience4j patterns: CircuitBreaker, RateLimiter, Bulkhead, TimeLimiter.  
- **dead-letter-pattern** — Kafka Dead-Letter Topic processing and recovery.  
- **output-box-pattern** — Outbox pattern for reliable event publication.  
- **keycloak-jwt-spring-boot**, **security-card-microservice** — OIDC with Keycloak, JWT validation, token propagation.  
- **refresh-instance-configuration-spring-boot** — Live config refresh for running instances.  
- **hr-core-subdomain**, **hr-microservice**, **hr-consumer**, **hr-async-consumer** — HR domain services with synchronous and asynchronous consumers.  
- **lottery-microservice**, **lottery-consumer** — Simple producer/consumer reference for messaging.  
- **study-… / world-… / spring-boot-…** — Focused labs for Actuator, JFR, JMX, JDBC/JPA/Template, WebClient, and reactive basics.  
- **Exercises** — Hands-on tasks used during the course.

> Naming note: some folders intentionally isolate one pattern (e.g., `dead-letter-pattern`) while others compose multiple building blocks (e.g., CRM/CQRS stack).

---

## Prerequisites

Install **Java 17+**, **Maven or Gradle** (use the wrapper if present), **Docker** (for infra like Kafka, DBs, Keycloak), and **Git**. A modern IDE such as IntelliJ IDEA is recommended. Different samples require different infra; start only what a project needs.

---

## Quick start

Clone and run any module as a standard Spring Boot app:

```bash
git clone https://github.com/deepcloudlabs/dcl355-2025-oct-21.git
cd dcl355-2025-oct-21

# Example: start discovery, config, and the gateway
(cd registry-service && ./mvnw spring-boot:run)
(cd config-server && ./mvnw spring-boot:run)
(cd reactive-gateway && ./mvnw spring-boot:run)

# Then start one or more domain services
(cd order-management-microservice && ./mvnw spring-boot:run)
```
If a project ships a `docker-compose.yml`, bring up its dependencies with:
```bash
docker compose up -d
```
Check each service’s `application.yml` for ports, profiles, and connection settings.

---

## Pattern guide (what to look for)

**Service discovery & centralized config.** `registry-service` and `config-server` demonstrate registering services and externalizing configuration so rollouts can be managed without rebuilding.

**Gatewaying and composition.** `reactive-gateway` routes traffic to backends, enabling edge cross-cutting concerns like timeouts, retries, and headers.

**Transactional messaging.** `output-box-pattern` implements the Outbox pattern so writes and event publishes are atomic; `dead-letter-pattern` shows robust error handling with DLT consumers.

**CQRS and read/write split.** The CRM projects separate write models from read models, with a reactive stream and a consumer that updates the query side.

**Resiliency.** `resiliency-patterns` uses Resilience4j to add CircuitBreaker, RateLimiter, Bulkhead, and TimeLimiter, along with fallbacks for graceful degradation.

**Security.** `keycloak-jwt-spring-boot` and `security-card-microservice` show OIDC login, JWT validation, and token forwarding between services.

**Observability.** The `study-*` projects illustrate Spring Boot Actuator endpoints, JFR profiling hooks, and JMX, helping you instrument services for operations.

---

## Exercises

Open the `Exercises` directory for the labs used in class. Each one references a target sample and a goal (e.g., add a circuit breaker to a slow route, wire an outbox publisher into a transactional flow, configure a DLT consumer, protect an endpoint with JWT, or expose health and metrics).

---

## Conventions

Each folder is a self-contained Spring Boot application. Use `./mvnw` or `./gradlew` to ensure consistent builds. Prefer profiles in `application.yml` for local vs. containerized setups. Keep infra minimal—start Kafka, DB, or Keycloak only for projects that need them.

---

## Support & credits

Created for **DEEPCLOUDLABS** training. If you have questions or spot issues, please open a GitHub issue in this repository. Thanks for participating and happy pattern-hunting!
