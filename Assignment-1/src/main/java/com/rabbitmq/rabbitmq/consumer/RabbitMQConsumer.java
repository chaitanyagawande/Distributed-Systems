package com.rabbitmq.rabbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.*;

@Component
public class RabbitMQConsumer {

    private final static String QUEUE_NAME = "rabbitmq";
    private AtomicInteger messageCount = new AtomicInteger(0);

    public void consume() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println("Waiting for messages. To exit, press CTRL+C");

            // Register a shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Total messages received: " + messageCount.get());
            }));

            consumeMessagesIndefinitely(channel);
        }
    }

    private void consumeMessagesIndefinitely(Channel channel) {
        try {
            while (true) { // Infinite loop to continuously consume messages
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println("Received: '" + message + "'");
                    messageCount.incrementAndGet();
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
            }
        } catch (IOException e) {
            System.out.println("Exception occurred while consuming message from RabbitMQ" + e.getMessage());
        }
    }
}

