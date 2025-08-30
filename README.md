# Data Anonymization with Spring Batch

This project is a Spring Boot application that uses **Spring Batch** to perform efficient data anonymization. It reads data from a PostgreSQL database, processes it in parallel across multiple jobs, and performs a simple form of anonymization (currently just printing the data).

The project includes a **Docker Compose** setup that orchestrates three main services:

- A PostgreSQL database instance  
- A Spring Batch application  
- A Python data generator to populate the database with sample data  

---

## Features

- **Parallel Job Execution:** Utilizes a `ThreadPoolTaskExecutor` to run multiple Spring Batch jobs concurrently for improved performance.  
- **Dockerized Environment:** Entire application stack (database, data generator, Spring Batch app) can be set up and run with a single command using Docker Compose.  
- **Spring Batch:** Structured to use Spring Batch for robust, scalable, and restartable batch processing.  
- **Data Ingestion:** A Python data generator script (`data_ingestion.py`) generates and ingests large volumes of synthetic data into the PostgreSQL database.  

---

## Project Structure

    DataAnonymization/
    ├── .mvn/
    ├── src/
    │ ├── main/
    │ │ ├── java/
    │ │ │ └── com/example/dataAnonymization/
    │ │ │ ├── config/ # Spring Batch and ThreadPool configuration
    │ │ │ ├── controller/ # REST controller to invoke jobs
    │ │ │ ├── dto/ # Data Transfer Objects
    │ │ │ ├── enums/ # SQL query enums
    │ │ │ ├── mapper/ # Row mappers
    │ │ │ ├── reader/ # Item readers for jobs
    │ │ │ ├── service/ # Service to orchestrate jobs
    │ │ │ └── writer/ # Item writers for jobs
    │ │ └── resources/
    │ │ └── application.properties # Application configuration
    │ └── test/
    ├── Dockerfile # Dockerfile for Spring Batch app
    ├── pom.xml # Maven configuration
    ├── schema-postgresql.sql # SQL schema for the database
    ├── data_ingestion.py # Python script to generate sample data
    ├── docker-compose.yml # Orchestrates all services
    └── Dockerfile # Dockerfile for Python data generator

---

## Getting Started

### Prerequisites

- Docker  
- Docker Compose  

---

### Running the Application

1. **Build and run the Docker Compose stack:**
   
   Navigate to the root directory of the repository and run:

   ```bash
   docker-compose up --build

  This command will:

  - Build and run the Python data generator to populate the database

  - Start a PostgreSQL database container

  - Build and start the Spring Batch application

2. **Verify Data Ingestion:**

   The Python data generator runs as a one-off service and shuts down after completing data ingestion.

   View logs to confirm:

        docker-compose logs data-generator


4. **Run the Anonymization Jobs:**
   
   Trigger the Spring Batch jobs via a POST request to the /runJobs endpoint:

       curl -X POST http://localhost:8080/runJobs


    Response:

        Anonymization jobs launched successfully!


5. **View Logs:**
   
   Monitor the Spring Batch container to see job execution and anonymized data:

        docker-compose logs spring-batch-app

---

### Note

  - Spring Batch jobs run in parallel using a thread pool for efficiency.

  - Python script data_ingestion.py generates large volumes of sample data.

  - Entire environment is fully Dockerized for easy setup.
