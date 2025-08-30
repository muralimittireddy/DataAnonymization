FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/batch-app.jar
COPY ${JAR_FILE} batch-app.jar
ENTRYPOINT ["java","-jar","batch-app.jar"]