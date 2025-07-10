// filepath: Lab-20250709/ecommerce-platform/README.md
# Ecommerce Platform Project

This project is a simple Spring Boot application as ecommerce-platform with basic CRUD operations.

## Structure
- Controller, DTO, Entity, Exception, Repository, and Service layers are organized under `src/main/java/com/example/`.
- Uses Maven for build and dependency management.

## How to Build and Run

1. Build the project:
   ```shell
   mvn clean install
   ```
2. Run the application:
   ```shell
   mvn spring-boot:run
   ```

## Requirements
- Java 17 or later
- Maven 3.6+

## Endpoints
- User and product CRUD endpoints are available under `/api/v1/`.

---

This is a template project. Implement your business logic in the respective layers.