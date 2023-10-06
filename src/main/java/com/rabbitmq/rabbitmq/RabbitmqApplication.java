package com.rabbitmq.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.rabbitmq.consumer.RabbitMQConsumer;
import com.rabbitmq.rabbitmq.producer.RabbitMQProducer;

@SpringBootApplication
public class RabbitmqApplication implements CommandLineRunner {

	@Autowired
	private RabbitMQProducer rabbitMQProducer;

	@Autowired
	private RabbitMQConsumer rabbitMQConsumer;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// publish 10000 messages to RabbitMQ
		rabbitMQProducer.publish();

		// Sleep for 5 seconds to allow the consumer to consume the messages
		Thread.sleep(30000);
		
		// consume messages from RabbitMQ
		rabbitMQConsumer.consume();
	}

}
