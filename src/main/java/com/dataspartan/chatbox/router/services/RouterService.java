package com.dataspartan.chatbox.router.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dataspartan.chatbox.router.kafka.consumer.RouterListener;
import com.dataspartan.chatbox.router.kafka.producer.RouterProducer;
import com.dataspartan.chatbox.router.model.MessengerMessageVO;
import com.dataspartan.chatbox.router.model.wit.entities.WitResult;
import com.dataspartan.chatbox.router.services.enums.ModulesEnum;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.send.MessengerSendClient;

@Component
public class RouterService {
	
    private static final Logger log = LoggerFactory.getLogger(RouterService.class);

	@Autowired
	private RouterProducer routerProducer;

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
		routerProducer.send(topicTo.getTopicTo(), record.value().toString());

	}

	/**
	 * 
	 * @param topicTo
	 * @param record
	 */
	public void sendTflTube(ConsumerRecord<String, MessengerMessageVO> record) {
		try {
			MessengerSendClient sendClient = MessengerPlatform.newSendClientBuilder("EAAcJwZC7SUf0BAMVvQo1DpjSVCRtAiwrJeDKSop9LmXz88Jk7qaPZCsxLuHbZCv7Ex4utR3qUzndtLFGC6kqdGKkXs5QRaUMeg82XuD7Bk9ZAcBTv7fCFkLxp2zsBDWCjZCcMhHkpNqRjXj8XeXPAUaSZAMh1OXE8EpYUi2gIY6AZDZD").build();
			sendClient.sendTextMessage(record.key(), "Hi there, how are you today?");
		} catch (Exception e) {
			log.error("sendTflTube",e);
		}

	}

}
