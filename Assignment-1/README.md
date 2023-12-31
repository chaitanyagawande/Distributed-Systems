# RabbitMQ

This Spring Boot application allows you to ensure that all messages produced by the producer are successfully received by the consumer using RabbitMQ messaging service. This README provides you with a step-by-step guide on setting up, configuring, and running the application.

## Prerequisites

Before you begin, ensure you have the following installed on your local machine:

- Java Development Kit (JDK) 8 or higher
- Apache Maven (for building the project)
- RabbitMQ Server (running and accessible)

### Running the Application

Open a terminal window and navigate to the project directory. Build and run the application using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start, and you can access it at `http://localhost:8080`. The producer will start sending messages, and the consumer will verify the reception.

## Screenshots

Here are some screenshots of the RabbitMQ Message Verification Tool in action:

**Producer Sending Messages:**

<kbd>![Producer](images/producer.png)</kbd>

**RabbitMQ Management UI:**

<kbd>![Consumer](images/rabbitmq.png)</kbd>

**Consumer Verifying Messages:**

<kbd>![Consumer](images/consumer.png)</kbd>
