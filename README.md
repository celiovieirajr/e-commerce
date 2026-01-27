# E-Commerce Core API

Backend project that simulates the core of an online sales platform (REST API) with focus on architectural best practices, database migrations using Liquibase, and automated tests.

Main domains:
- Product management (CRUD)
- Sales and sale items management
- Customer management
- Integration with the ViaCEP API (postal code lookup)
- Continuous evolution with DB migrations (Liquibase)

---

## Overview

1. [Stack & Versions](#stack--versions)
2. [High-Level Architecture](#high-level-architecture)
3. [Folder Structure](#folder-structure)
4. [Quick Start](#quick-start)
5. [Configuration and Profiles (homolog / prod)](#configuration-and-profiles-homolog--prod)
6. [Liquibase — Best Practices & Troubleshooting](#liquibase--best-practices--troubleshooting)
7. [Testing & Quality](#testing--quality)
8. [Continuous Integration (CI) / CD Plans](#continuous-integration-ci--cd-plans)
9. [Roadmap (future)](#roadmap-future)
10. [License](#license)

---

## Stack & Versions

Recommended for this project:

- JVM & Framework: Java 23 (LTS) · Spring Boot 4.0.2
- Build: Maven
- Database (dev/homolog): H2 (in-memory)
- Database (prod): MySQL
- Migrations: Liquibase (YAML changelogs)
- Web client: Spring Web / WebFlux (WebClient when required)
- Validation: Jakarta Validation (Bean Validation)
- Lombok: optional to reduce boilerplate
- Testing: JUnit 5, Mockito

---

## High-Level Architecture

Monolithic API organized by domain modules: products, customers, sales, itemSales, viaceps.
Responsibilities follow classic layers: controller → service → repository → entity.
Liquibase handles schema creation at startup; JPA/Hibernate maps entities.

---

## Folder Structure (summary)

```
src/
  main/
    java/com/example/demo/             # application & configs
      modules/                          # domains: customers, products, sales...
      config/                           # WebClient, beans, etc
    resources/
      application.yml                   # common configuration
      application-homolog.yml           # H2 / homolog
      application-prod.yml              # MySQL / production
      liquibase/
        db.changelog-homolog.yml        # homolog master changelog
        db.changelog-prod.yml           # prod master changelog
        changes/                         # changelog fragments per changeSet
```

---

## Quick Start

Prerequisites: JDK 23+, Maven 3.9+

Run with homolog profile (H2):

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=homolog
```

Run with prod profile (MySQL) — configure credentials in application-prod.yml or via environment variables:

```
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

Run the jar with a profile:

```
java -jar -Dspring.profiles.active=homolog target/demo-0.0.1-SNAPSHOT.jar
```

Access H2 Console (when homolog is active): http://localhost:3000/h2-console
Default JDBC URL: jdbc:h2:mem:homolog-db

---

## Configuration and Profiles (homolog / prod)

Best practices used in this project:

- application.yml contains shared properties.
- application-homolog.yml: configures H2, liquibase.change-log should point to `classpath:liquibase/db.changelog-homolog.yml` and must use `spring.config.activate.on-profile: homolog`.
- application-prod.yml: configures MySQL; use environment variables for credentials and `spring.config.activate.on-profile: prod`.
- Do not hardcode `spring.profiles.active` in production.

Important example to avoid circular dependency between Liquibase and JPA:

```
spring:
  jpa:
    defer-datasource-initialization: true
```

---

## Liquibase — Best Practices & Troubleshooting

- Place all YAML changelogs under `src/main/resources/liquibase/` and use relative paths in includes.
- Validate YAML formatting (indentation). YAML errors will prevent Liquibase from parsing.
- Check the `DATABASECHANGELOG` table in the database to confirm execution.
- Set `spring.jpa.defer-datasource-initialization: true` so Liquibase runs before the EntityManagerFactory is created.
- Use `logging.level.liquibase=DEBUG` to troubleshoot which changelogs are being searched and loaded.

---

## Testing & Quality

- Unit tests: JUnit 5 + Mockito. Use `ArgumentMatchers` (for example `anyString()`) when the exact argument is not relevant.
- To avoid strict stubbing issues with Mockito, use `doReturn(...).when(...)` or `lenient()` where appropriate.
- Integrate JaCoCo and Checkstyle into the CI pipeline.

---

## Continuous Integration (CI) / CD Plans

CI pipeline example (GitHub Actions):

- Run on Java 23
- Execute `mvn -B clean verify`
- Publish artifact and run migrations in the pipeline before deploy (with a migration user)

Future CD plans:
- Build Docker image and automate deploy to homolog environment
- Controlled production rollout (blue/green or canary)

---

## Roadmap (future)

- Consolidate changelogs and controlled rollbacks
- Metrics (Prometheus) + health checks
- Full OpenAPI/Swagger documentation
- Infrastructure-as-code for homolog/prod environments

---

## License

MIT License — see LICENSE file

---

If you want, I can:
1) Update application-homolog.yml and application-prod.yml with ready examples.
2) Validate Liquibase files in resources and fix includes (paths).
3) Create a basic GitHub Actions CI workflow.

Tell me which option you want me to perform next.
