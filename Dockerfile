# Stage 1: Build JAR
FROM maven:3.9.1-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Create runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/batch-app.jar batch-app.jar
ENTRYPOINT ["java", "-jar", "batch-app.jar"]
