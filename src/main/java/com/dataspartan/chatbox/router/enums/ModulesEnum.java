package com.dataspartan.chatbox.router.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ModulesEnum {

	MODULE_NOT_FOUND(), TFL_MODULE("tfl-message-processor", new HashSet<String>(Arrays.asList("tubestatus_get")));

	private String topicTo;
	private Set<String> intents;

	ModulesEnum() {
	}

	ModulesEnum(String topicTo, Set<String> intents) {
		this.topicTo = topicTo;
		this.intents = intents;
	}

	public String getTopicTo() {
		return topicTo;
	}

	public Set<String> getIntents() {
		return intents;
	}

	/**
	 * Find module by intent
	 * 
	 * @param intent
	 * @return
	 */
	public static ModulesEnum findByIntent(String intent) {
		for (ModulesEnum e : values()) {
			if (!ModulesEnum.MODULE_NOT_FOUND.equals(e) && e.getIntents().contains(intent))
				return e;
		}
		return ModulesEnum.MODULE_NOT_FOUND;
	}

}
