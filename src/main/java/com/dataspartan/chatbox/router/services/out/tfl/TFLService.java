package com.dataspartan.chatbox.router.services.out.tfl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataspartan.chatbox.router.model.MessengerMessageVO;
import com.dataspartan.chatbox.router.services.out.tfl.enums.StationsEnum;
import com.dataspartan.chatbox.router.services.out.tfl.hbase.HBaseClient;
import com.dataspartan.chatbox.router.services.out.tfl.utils.StringSimilarity;
import com.dataspartan.chatbox.router.utils.Pair;
import com.dataspartan.chatbox.router.utils.SystemUtil;
import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.send.MessengerSendClient;

@Service
public class TFLService {

	private static final Logger log = LoggerFactory.getLogger(TFLService.class);
	private static String pageAccessToken = null;
	
	@Autowired
	private HBaseClient hbase;

	/**
	 * 
	 * @param topicTo
	 * @param record
	 */
	public void send(ConsumerRecord<String, MessengerMessageVO> record) {
		try {
			Pair<String, StationsEnum> stationInfo = getStation(record.value());
			String message = composeMessage(stationInfo);
			this.sendMessage(record.key(), message);

		} catch (Exception e) {
			log.error("Error sending message about tfl tube status to user", e);
		}

	}

	/**
	 * Get the station
	 * 
	 * @param messageVO
	 * @return
	 */
	private Pair<String, StationsEnum> getStation(MessengerMessageVO message) {
		String tube_station = null;
		StationsEnum stationEnum = null;

		if (message != null && message.getNlp() != null && message.getNlp().getEntities() != null
				&& message.getNlp().getEntities().get("tube_station") != null
				&& message.getNlp().getEntities().get("tube_station").length > 0) {

			stationEnum = StationsEnum.NOT_FOUND;
			tube_station = message.getNlp().getEntities().get("tube_station")[0].getValue();
			double maxSimilarity = 0.0;
			for (StationsEnum station : StationsEnum.values()) {
				double similarity = StringSimilarity.similarity(tube_station, station.getName());
				if (similarity > 0.5 && similarity > maxSimilarity) {
					stationEnum = station;
					maxSimilarity = similarity;
				}

			}
		}
		return new Pair<String, StationsEnum>(tube_station, stationEnum);
	}

	/**
	 * Compose message for return to the user
	 * 
	 * @param stationEnum
	 * @return
	 */
	private String composeMessage(Pair<String, StationsEnum> stationInfo) throws Exception {
		String result = "This default message shouldnt appear!";

		if (stationInfo.getA() == null) {
			result = "Please, write the station name.";
		} else if (StationsEnum.NOT_FOUND.equals(stationInfo.getB())) {
			result = String.format("Sorry, We can not find any station with name %s.", stationInfo.getA());
		} else {
			String status = hbase.getStatusSeverityDescription(stationInfo.getB());
			if (status == null) {
				result = String.format("Sorry, We do not have information about %s station.", stationInfo.getB().getName());
			} else {
				result = String.format("The %s station has %s.", stationInfo.getB().getName(), status);
			}
		}

		return result;
	}

	/**
	 * Send message to the user
	 * 
	 * @param message
	 */
	private void sendMessage(String userId, String message) {
		try {
			if (pageAccessToken == null)
				pageAccessToken = SystemUtil.getEnv("MESSENGER_PAGE_ACCESS_TOKEN",
					"EAAcJwZC7SUf0BAMVvQo1DpjSVCRtAiwrJeDKSop9LmXz88Jk7qaPZCsxLuHbZCv7Ex4utR3qUzndtLFGC6kqdGKkXs5QRaUMeg82XuD7Bk9ZAcBTv7fCFkLxp2zsBDWCjZCcMhHkpNqRjXj8XeXPAUaSZAMh1OXE8EpYUi2gIY6AZDZD");
			MessengerSendClient sendClient = MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
			sendClient.sendTextMessage(userId, message);
		} catch (Exception e) {
			log.error("Error Sending message to user", e);
		}
	}

}
