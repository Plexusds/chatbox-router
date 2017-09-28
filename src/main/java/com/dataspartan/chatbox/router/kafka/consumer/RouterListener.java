package com.dataspartan.chatbox.router.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.dataspartan.chatbox.router.model.MessengerMessageVO;
import com.dataspartan.chatbox.router.services.RouterService;
import com.dataspartan.chatbox.router.services.enums.ModulesEnum;

public class RouterListener {
	
    private static final Logger log = LoggerFactory.getLogger(RouterListener.class);

	@Autowired
	private RouterService routerService;
	
	@KafkaListener(topics = "messenger-message-inbox")
	public void listen(ConsumerRecord<String, MessengerMessageVO> record) {
		try {
			log.info(String.format("\nMessageId: [%s] \nUserId %s \nMessage: %s received", record.value().getMid(), record.key(), record.value()));
			ModulesEnum topicTo = routerService.getTopic(record.value());
			log.info(String.format("\\nMessageId: [%s] \nTopicTo: %s", record.value().getMid(), topicTo.getTopicTo()));
			routerService.sendTflTube(record);
			
		} catch (Exception e) {
			log.error(String.format("\nError processing MessageId: [%s] ", record.value().getMid()), e);
		}

	}

}