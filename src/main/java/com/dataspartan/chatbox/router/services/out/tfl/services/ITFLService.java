package com.dataspartan.chatbox.router.services.out.tfl.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.dataspartan.chatbox.router.model.MessengerMessageVO;

public interface ITFLService {

	/**
	 * 
	 * @param topicTo
	 * @param record
	 */
	public void send(ConsumerRecord<String, MessengerMessageVO> record);

}
