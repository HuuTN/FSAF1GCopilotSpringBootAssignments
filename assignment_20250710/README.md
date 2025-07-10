# User Management Spring Boot Application

## Requirements
- Java 17
- PostgreSQL
- Gradle

## Setting
1. Clone project
2. config database at `src/main/resources/application.properties`
3. Run:
```bash
./gradlew bootRun
```

## API
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI docs: http://localhost:8080/api-docs

## endpoints
- `GET /api/v1/users` (có paging)
- `GET /api/v1/users/{id}`
- `POST /api/v1/users`
- `PUT /api/v1/users/{id}`
- `DELETE /api/v1/users/{id}` 