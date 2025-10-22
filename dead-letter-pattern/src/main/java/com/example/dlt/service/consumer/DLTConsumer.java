package com.example.dlt.service.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class DLTConsumer {

	@KafkaListener(topics = "orders.DLT", groupId = "orders-dlt-consumers")
	public void onDlt(String message) {
		System.err.println("Dead Letter Message: %s".formatted(message));
	}
}