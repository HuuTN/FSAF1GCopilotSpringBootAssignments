# Use OpenJDK 23 as the base image
FROM eclipse-temurin:23-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
