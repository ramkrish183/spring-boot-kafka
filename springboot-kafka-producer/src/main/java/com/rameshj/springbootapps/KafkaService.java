package com.rameshj.springbootapps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaService {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaTemplate<String, Product> productKafkaTemplate;

	private static final String TOPIC = "NEWTOPIC";

	public String publishMessage(final String message) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			public void onSuccess(SendResult<String, String> result) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});

		return "SUCCESS";
	}

	public String publishObject(final Product product) {
		ListenableFuture<SendResult<String, Product>> future = productKafkaTemplate.send(TOPIC, product);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Product>>() {

			public void onSuccess(SendResult<String, Product> result) {
				System.out.println(
						"Sent message=[" + product + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + product + "] due to : " + ex.getMessage());
			}
		});

		return "SUCCESS";
	}

}
