The E-Commerce Core API simulates the core of an online sales platform, covering
real-world use cases such as:

 - Product management (create, update, list, delete)
 - Sales management
 - Sale item management
 - Customer management
 - ZIP code lookup via the VIACEP API
 - Continuous evolution with clean-code and architecture best practices

The project is suitable for learning, portfolio, and as a foundation for larger
systems.

-------------------------------------------------------------------------------------

Summary

 - Stack & Versions (#-stack--versions)
 - Architecture (#-architecture)
 - System Domains (#-system-domains)
 - Project Structure (#-project-structure)
 - Swagger Documentation (#-swagger-documentation)
 - Configuration (#-configuration)
 - Running Locally (#-running-locally)
 - CI & CD (#-ci--cd)
 - Quality Gates (#-quality-gates)
 - Roadmap (#-roadmap)
 - License (#-license)

-------------------------------------------------------------------------------------

🛠 Stack & Versions

┌─────────────┬───────────────────┐
│ Layer       │ Technology        │
├─────────────┼───────────────────┤
│ Language    │ Java 23           │
├─────────────┼───────────────────┤
│ Framework   │ Spring Boot 3.3.x │
├─────────────┼───────────────────┤
│ API         │ Spring Web (REST) │
├─────────────┼───────────────────┤
│ Persistence │ Spring Data JPA   │
├─────────────┼───────────────────┤
│ Database    │ H2 (homolog)      │
├─────────────┼───────────────────┤
│ Docs        │ Springdoc OpenAPI │
├─────────────┼───────────────────┤
│ Build       │ Maven             │
├─────────────┼───────────────────┤
│ CI/CD       │ GitHub Actions    │
└─────────────┴───────────────────┘

-------------------------------------------------------------------------------------

🏗 Architecture

 flowchart TD
     Client[Client / Frontend] --> Controller[Controllers]
     Controller --> Service[Services]
     Service --> Repository[Repositories]
     Repository --> Database[(Database)]

     Controller --> Swagger[Swagger UI]

The architecture follows a layered approach (Controller → Service → Repository),
aligned with clean architecture principles and REST best practices.

-------------------------------------------------------------------------------------

🧱 System Domains

Implemented CRUDs

┌──────────┬────────────────────────────────────┐
│ Entity   │ Description                        │
├──────────┼────────────────────────────────────┤
│ Product  │ Products available for sale        │
├──────────┼────────────────────────────────────┤
│ Customer │ Customer data and basic profile    │
├──────────┼────────────────────────────────────┤
│ Sale     │ Placed orders (sales)              │
├──────────┼────────────────────────────────────┤
│ ItemSale │ Items that belong to a given order │
└──────────┴────────────────────────────────────┘

General Features

 - Full CRUD operations for the main entities
 - Validation using Bean Validation (Jakarta Validation)
 - Pagination and sorting for list endpoints
 - Global exception handling with standardized error responses
 - RESTful resource modeling and HTTP status codes

-------------------------------------------------------------------------------------

📂 Project Structure

 ecommerce-core/
 ├─ .github/
 │  └─ workflows/
 │     ├─ ci.yml
 │     └─ cd.yml
 ├─ src/main/java/com/example/ecommerce/
 │  ├─ controller/
 │  ├─ service/
 │  ├─ repository/
 │  ├─ model/
 │  ├─ dto/
 │  ├─ mapper/
 │  └─ config/
 ├─ src/main/resources/
 │  ├─ application.yml
 │  ├─ application-dev.yml
 │  └─ application-prod.yml
 ├─ src/test/java/
 ├─ pom.xml
 └─ README.md

-------------------------------------------------------------------------------------

📑 Swagger Documentation

The API is documented using Swagger / OpenAPI via Springdoc.

Access:

 http://localhost:3000/swagger-ui.html

or

 http://localhost:3000/swagger-ui/index.html

Documented resources include:

 - REST endpoints
 - HTTP methods
 - Path and query parameters
 - Example requests and responses
 - HTTP status codes

OpenAPI JSON:

 http://localhost:3000/v3/api-docs

-------------------------------------------------------------------------------------

⚙️ Configuration

Prerequisites:

 - Java 23 installed and configured in PATH
 - Maven (or Maven Wrapper via mvnw) available

Main configuration files:

 - application.yml: base configuration
 - application-dev.yml: development profile (H2 database)
 - application-prod.yml: production-oriented settings

Profiles can be selected via the spring.profiles.active property.

-------------------------------------------------------------------------------------

▶️ Running Locally

Build the project:

 mvn clean install

Run the application (default dev profile):

 mvn spring-boot:run

After startup, the API and Swagger UI will be available on port 3000 (or the port
configured in application*.yml).

-------------------------------------------------------------------------------------

🔄 CI & CD

Continuous Integration (CI) via GitHub Actions (.github/workflows/ci.yml):

 - Triggered on:
   - Push to main and develop
   - Pull Requests
 - Steps:
   - Project build
   - Test execution
   - (Optional) Quality analysis and artifact generation

Continuous Delivery (CD) via GitHub Actions (.github/workflows/cd.yml):

 - Automated deployment after CI success and approval
 - Ready to integrate with cloud environments
 - Prepared for container-based deployments (Docker, EC2, etc.)

-------------------------------------------------------------------------------------

✅ Quality Gates

The pipeline is designed to support quality gates such as:

 - All unit tests passing
 - Successful project build
 - (Optional) Static analysis and code coverage checks

These checks help ensure that only healthy builds progress to deployment.

-------------------------------------------------------------------------------------

🗺 Roadmap

Some possible next steps for evolution:

 - Authentication and authorization (e.g., Spring Security, JWT)
 - Product categories and inventory management
 - Payment and invoice integration
 - More advanced reporting and metrics
 - Dockerization and full cloud deployment configuration

-------------------------------------------------------------------------------------

📜 License

This project is open source.
Check the LICENSE file in the repository root for detailed licensing information.
