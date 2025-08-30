# Stage 1: Build JAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/DataAnonymization-0.0.1-SNAPSHOT.jar batch-app.jar
ENTRYPOINT ["java", "-jar", "batch-app.jar"]
