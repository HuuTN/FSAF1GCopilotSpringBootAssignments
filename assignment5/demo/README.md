# User Management System Backend

This is a RESTful backend for a user management system built with:
- Java 17
- Spring Boot 3.x
- MySQL
- Lombok
- Swagger (OpenAPI 3)

## Features
- User CRUD operations (Create, Read, Update, Delete)
- MySQL database integration
- API documentation with Swagger UI
- Lombok for boilerplate code reduction

## Getting Started
1. Update your MySQL credentials in `src/main/resources/application.properties`.
2. Run the application with:
   ```
   ./mvnw spring-boot:run
   ```
3. Access Swagger UI at: `http://localhost:8080/swagger-ui.html`

## API Endpoints
- `GET /api/users` - List all users
- `GET /api/users/{id}` - Get user by ID
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

## Notes
- Make sure MySQL is running and a database named `userdb` exists.
- Default username: `root`, password: `your_password` (change as needed).
