package com.rameshj.springbootapps.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.rameshj.springbootapps.Product;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "OLDTOPIC", groupId = "group_id")
	public void consume(String message) {
		System.out.println("Message : " + message);
	}

	@KafkaListener(topics = "NEWTOPIC", containerFactory = "productKafkaListenerContainerFactory")
	public void greetingListener(Product productInfo) {
		System.out.println("hello " + productInfo.getDescription());
	}
}
