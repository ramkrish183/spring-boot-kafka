package com.rameshj.springbootapps.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> mapConfig = new HashMap<String, Object>();
		mapConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		mapConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		mapConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		mapConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

		return new DefaultKafkaConsumerFactory<String, String>(mapConfig);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> concurrentKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	
	@Bean
	public ConsumerFactory<String,JsonDeserializer> productconsumerFactory(){
		Map<String, Object> mapConfig = new HashMap<String, Object>();
		mapConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		mapConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		mapConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		mapConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class );
		mapConfig.put(JsonDeserializer.TRUSTED_PACKAGES, "com.rameshj.springbootapps");
		return new DefaultKafkaConsumerFactory<String, JsonDeserializer>(mapConfig);	
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, JsonDeserializer>> productKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, JsonDeserializer> factory = new ConcurrentKafkaListenerContainerFactory<String, JsonDeserializer>();
		factory.setConsumerFactory(productconsumerFactory());
		return factory;
	}
}
