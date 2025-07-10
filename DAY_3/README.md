# DAY_3 Project

## Overview
DAY_3 is a Spring Boot application that demonstrates the use of Spring Web, Spring Data JPA, Lombok, and MySQL. This project serves as a template for building web applications with a focus on RESTful services and database interactions.

## Features
- RESTful API endpoints for handling HTTP requests.
- JPA for database interactions with MySQL.
- Lombok for reducing boilerplate code in model classes.
- Static resources support for serving CSS, JavaScript, and images.
- Template support for rendering views.

## Project Structure
```
DAY_3
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── day3
│   │   │               ├── Day3Application.java
│   │   │               ├── controller
│   │   │               ├── model
│   │   │               └── repository
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── day3
│                       └── Day3ApplicationTests.java
├── pom.xml
└── README.md
```

## Setup Instructions
1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd DAY_3
   ```

2. **Configure MySQL Database**:
   - Create a new MySQL database for the application.
   - Update the `src/main/resources/application.properties` file with your database connection details.

3. **Build the Project**:
   ```
   mvn clean install
   ```

4. **Run the Application**:
   ```
   mvn spring-boot:run
   ```

## Usage
- Access the application at `http://localhost:8080`.
- Use tools like Postman or curl to interact with the RESTful API endpoints.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.