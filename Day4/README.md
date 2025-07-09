# Spring Boot User Application

This project is a Spring Boot application designed to manage user entities. It provides a RESTful API for performing CRUD operations on users, utilizing best practices in software development.

## Project Structure

```
spring-boot-user-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── userapp
│   │   │               ├── UserAppApplication.java
│   │   │               ├── controller
│   │   │               │   └── UserController.java
│   │   │               ├── dto
│   │   │               │   └── UserDTO.java
│   │   │               ├── entity
│   │   │               │   └── User.java
│   │   │               ├── exception
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── UserNotFoundException.java
│   │   │               ├── repository
│   │   │               │   └── UserRepository.java
│   │   │               ├── response
│   │   │               │   └── ErrorResponse.java
│   │   │               └── service
│   │   │                   ├── UserService.java
│   │   │                   └── UserServiceImpl.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── userapp
│                       └── service
│                           └── UserServiceTest.java
├── .github
│   └── copilot-instructions.md
├── Dockerfile
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd spring-boot-user-app
   ```

2. **Build the Project**
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   You can run the application using:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**
   The API will be available at `http://localhost:8080/api/v1/users`.

## Usage

- **Create User**: `POST /api/v1/users`
- **Get All Users**: `GET /api/v1/users`
- **Get User by ID**: `GET /api/v1/users/{id}`
- **Update User**: `PUT /api/v1/users/{id}`
- **Delete User**: `DELETE /api/v1/users/{id}`

## Testing

JUnit tests are included in the project to ensure the functionality of the UserService. You can run the tests using:
```bash
mvn test
```

## Docker

To build a Docker image for the application, run:
```bash
docker build -t spring-boot-user-app .
```

## License

This project is licensed under the MIT License.