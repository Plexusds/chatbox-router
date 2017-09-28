package com.dataspartan.chatbox.router.services.out.tfl.enums;

public enum StationsEnum {

	NOT_FOUND(),
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
	
	StationsEnum() { }
	StationsEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	
}
