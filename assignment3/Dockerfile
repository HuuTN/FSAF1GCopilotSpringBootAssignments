# Dockerfile for Spring Boot application with openjdk 17
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/assignment3-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
