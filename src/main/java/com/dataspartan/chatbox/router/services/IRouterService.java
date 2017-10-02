package com.dataspartan.chatbox.router.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dataspartan.chatbox.router.enums.ModulesEnum;
import com.dataspartan.chatbox.router.model.MessengerMessageVO;

public interface IRouterService {

	/**
	 * Get the output module to send this message
	 * 
	 * @param message
	 * @return
	 */
	public ModulesEnum getTopic(MessengerMessageVO message);

	/**
	 * 
	 * @param topicTo
	 * @param record
	 */
	public void send(ModulesEnum topicTo, ConsumerRecord<String, MessengerMessageVO> record);

}
