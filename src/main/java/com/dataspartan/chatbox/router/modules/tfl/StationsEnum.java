package com.dataspartan.chatbox.router.modules.tfl;

public enum StationsEnum {

	BAKERLOO("bakerloo", "Bakerloo"),
    CENTRAL("central", "Central"),
    CIRCLE("circle", "Circle"),
    DISTRICT("district", "District"),
    HAMMERSMITH_AND_CITY("hammersmith-city", "Hammersmith & City"),
    JUBILEE("jubilee", "Jubilee"),
    METROPOLITAN("metropolitan", "Metropolitan"),
    NORTHERN("northern", "Northern"),
    PICADILLY("piccadilly", "Piccadilly"),
    VICTORIA("victoria", "Victoria"),
    WATERLOO_AND_CITY("waterloo-city", "Waterloo & City");
	
	private String id;
	private String name;
	
	StationsEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
