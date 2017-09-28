package com.dataspartan.chatbox.router.model.wit.entities;

public class WitResult {
	
	private Boolean suggested;
	private Double confidence;
	private String value;
	private String type;
	
	public WitResult() {
		super();
	}
	
	public Boolean getSuggested() {
		return suggested;
	}
	public void setSuggested(Boolean suggested) {
		this.suggested = suggested;
	}
	public Double getConfidence() {
		return confidence;
	}
	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "WitResult [suggested=" + suggested + ", confidence=" + confidence + ", value=" + value + ", type="
				+ type + "]";
	}
	

}
