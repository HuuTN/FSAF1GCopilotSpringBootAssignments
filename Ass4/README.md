# DemoJPA Spring Boot Project

This project is a Spring Boot JPA application (JDK 19) with the following features:
- Product and Category entities with @ManyToOne relationship
- Category entity with self-referencing parent-child relationship
- Order and OrderItem entities with @OneToMany relationship
- ProductRepository with custom JPQL, native SQL queries, and pagination
- MySQL connection and connection pooling (see application.yml)
- data.sql for initial sample data
- Base entity with audit fields (@CreatedDate, @LastModifiedDate)
- Fetching strategies (LAZY/EAGER)
- Bidirectional mapping between Order and User

## Getting Started
1. Configure your MySQL database in `src/main/resources/application.yml` (to be created).
2. Build and run the project:
   ```bash
   ./mvnw clean spring-boot:run
   ```
3. Access the API endpoints as implemented.

## Tasks
See `.github/copilot-instructions.md` for Copilot-specific instructions.
