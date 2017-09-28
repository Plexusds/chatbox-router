package com.dataspartan.chatbox.router.model.wit;

import java.util.Map;
import java.util.stream.Collectors;

import com.dataspartan.chatbox.router.model.wit.entities.WitResult;

public class NaturalLanguageProcessingVO {

	/** Message ID */
	private Map<String, WitResult[]> entities;

	public NaturalLanguageProcessingVO() {
		super();
	}

	public Map<String, WitResult[]> getEntities() {
		return entities;
	}

	public void setEntities(Map<String, WitResult[]> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		String str = "null";
		if (entities != null)
			str = entities.entrySet().stream().map(entry -> entry.getKey() + " - " + arrayToString(entry.getValue()))
					.collect(Collectors.joining(", "));
		return "NaturalLanguageProcessingVO [entities=" + str + "]";
	}

	private String arrayToString(WitResult[] array) {
		if (array == null)
			return "null";
		String result = "";
		for (WitResult str : array) {
			result += str.toString();
		}
		return result;
	}

}
