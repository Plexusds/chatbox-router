package com.dataspartan.chatbox.router.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataspartan.chatbox.router.kafka.producer.RouterProducer;
import com.dataspartan.chatbox.router.model.MessengerMessageVO;
import com.dataspartan.chatbox.router.model.wit.entities.WitResult;
import com.dataspartan.chatbox.router.services.enums.ModulesEnum;
import com.dataspartan.chatbox.router.services.out.tfl.TFLService;

@Service
public class RouterService {

	private static final Logger log = LoggerFactory.getLogger(RouterService.class);

	@Autowired
	private RouterProducer routerProducer;
	@Autowired
	private TFLService tflService;

	/**
	 * Get the output module to send this message
	 * 
	 * @param message
	 * @return
	 */
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

	/**
	 * 
	 * @param topicTo
	 * @param record
	 */
	public void send(ModulesEnum topicTo, ConsumerRecord<String, MessengerMessageVO> record) {
		tflService.send(record);
	}

}
