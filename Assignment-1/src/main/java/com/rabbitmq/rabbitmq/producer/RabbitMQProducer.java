package com.rabbitmq.rabbitmq.producer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RabbitMQProducer {

    private final static String QUEUE_NAME = "rabbitmq";

    public void publish() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            publishMessage10000Times(channel);
        }
    }

    private void publishMessage10000Times(Channel channel) throws IOException {
        AtomicInteger messageCount = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {
            try {
                String message = "Hello, RabbitMQ! " + i;
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Sent: '" + message + "'");
                messageCount.incrementAndGet();
            } catch (Exception e) {
                System.out.println("Exception occurred while sending message to RabbitMQ" + e.getMessage());
            }
        }
        System.out.println("Total messages sent: " + messageCount.get());
    }
}

