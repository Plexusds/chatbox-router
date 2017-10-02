package com.dataspartan.chatbox.router.services.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataspartan.chatbox.router.enums.ModulesEnum;
import com.dataspartan.chatbox.router.model.MessengerMessageVO;
import com.dataspartan.chatbox.router.model.wit.entities.WitResult;
import com.dataspartan.chatbox.router.services.IRouterService;
import com.dataspartan.chatbox.router.services.out.tfl.services.ITFLService;

@Service
public class RouterServiceImpl implements IRouterService {

	@Autowired
	private ITFLService tflService;

	public ModulesEnum getTopic(MessengerMessageVO message) {
		ModulesEnum result = ModulesEnum.MODULE_NOT_FOUND;
		if (message != null && message.getNlp() != null && message.getNlp().getEntities() != null
				&& message.getNlp().getEntities().get("intent") != null) {
			Double maxconfidence = 0.0;
			for (WitResult e : message.getNlp().getEntities().get("intent")) {
				if (e.getConfidence() > maxconfidence) {
					ModulesEnum module = ModulesEnum.findByIntent(e.getValue());
					if (!ModulesEnum.MODULE_NOT_FOUND.equals(module)) {
						result = module;
						maxconfidence = e.getConfidence();
					}
				}
			}
		}
		return result;
	}

	public void send(ModulesEnum topicTo, ConsumerRecord<String, MessengerMessageVO> record) {
		tflService.send(record);
	}

}
