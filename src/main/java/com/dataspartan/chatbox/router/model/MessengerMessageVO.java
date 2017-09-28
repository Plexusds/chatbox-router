package com.dataspartan.chatbox.router.model;

import com.dataspartan.chatbox.router.model.wit.NaturalLanguageProcessingVO;

public class MessengerMessageVO {
	
	private final String source = "chatbox-messenger-app";
	/** Message ID */
	private String mid;
	/** Message text */
	private String text;
	/** The natural language processing by wit.ai */
	private NaturalLanguageProcessingVO nlp;
	
	public MessengerMessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getSource() {
		return source;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public NaturalLanguageProcessingVO getNlp() {
		return nlp;
	}
	public void setNlp(NaturalLanguageProcessingVO nlp) {
		this.nlp = nlp;
	}

	@Override
	public String toString() {
		return "MessengerMessageVO [source=" + source + ", mid=" + mid + ", text=" + text + ", nlp=" + nlp + "]";
	}
	
}
