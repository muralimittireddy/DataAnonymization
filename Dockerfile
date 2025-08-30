# Python 3.11 slim base
FROM python:3.11-slim

# Set working directory
WORKDIR /app

# Copy script
COPY data_ingestion.py .

# Install dependencies
RUN pip install psycopg2-binary faker

# Run script
CMD ["python", "data_ingestion.py"]


