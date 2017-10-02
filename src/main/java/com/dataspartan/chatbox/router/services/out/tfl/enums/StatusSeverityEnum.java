package com.dataspartan.chatbox.router.services.out.tfl.enums;

public enum StatusSeverityEnum {

	NOT_FOUND(),
	SPECIAL_SERVICE(0 ,"Special Service"),
	CLOSED(1 ,"Closed"),
	SUSPENDED(2 ,"Suspended"),
	PART_SUSPENDED(3 ,"Part Suspended"),
	PLANNED_CLOSURE(4 ,"Planned Closure"),
	PART_CLOSURE(5 ,"Part Closure"),
	SEVERE_DELAYS(6 ,"Severe Delays"),
	REDUCED_SERVICE(7 ,"Reduced Service"),
	BUS_SERVICE(8 ,"Bus Service"),
	MINOR_DELAYS(9 ,"Minor Delays"),
	GOOD_SERVICE(10 ,"Good Service"),
	PART_CLOSED(11 ,"Part Closed"),
	EXIT_ONLY(12 ,"Exit Only"),
	NO_STEP_FREE_ACCESS(13 ,"No Step Free Access"),
	CHANGE_OF_FREQUENCY(14 ,"Change of frequency"),
	DIVERTED(15 ,"Diverted"),
	NOT_RUNNING(16 ,"Not Running"),
	ISSUES_REPORTED(17 ,"Issues Reported"),
	NO_ISSUES(18 ,"No Issues"),
	INFORMATION(19 ,"Information"),
	SERVICE_CLOSED(20 ,"Service Closed"),
	;

	private int severityLevel;
	private String description;

	StatusSeverityEnum() {}
	StatusSeverityEnum(int severityLevel, String description) {
		this.severityLevel = severityLevel;
		this.description = description;
	}

	public int getSeverityLevel() {
		return severityLevel;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Get the severity by the serverity level
	 * 
	 * @param severityLevel
	 * @return
	 */
	public static StatusSeverityEnum get(int severityLevel) {
		for (StatusSeverityEnum e : values()) {
			if (e.getSeverityLevel() == severityLevel) {
				return e;
			}
		}
		return StatusSeverityEnum.NOT_FOUND;
	}

}
