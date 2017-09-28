package com.dataspartan.chatbox.router.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.dataspartan.chatbox.router.kafka.consumer.RouterListener;
import com.dataspartan.chatbox.router.model.MessengerMessageVO;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "HortonWorks21:6667");
		propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-consumer-messenger-message-inbox-group");
		propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return propsMap;
	}

	@Bean
	public ConsumerFactory<String, MessengerMessageVO> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(MessengerMessageVO.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MessengerMessageVO> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MessengerMessageVO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public RouterListener listener() {
		return new RouterListener();
	}

}