package com.dataspartan.chatbox.router.services.out.tfl.hbase;

import com.dataspartan.chatbox.router.services.out.tfl.enums.StationsEnum;
import com.dataspartan.chatbox.router.services.out.tfl.enums.StatusSeverityEnum;
import com.dataspartan.chatbox.router.utils.Pair;

public interface IHBaseClient {

	/**
	 * Get status severity enum
	 * 
	 * @param station
	 * @return
	 * @throws Exception
	 */
	public StatusSeverityEnum getStatusSeverity(StationsEnum station) throws Exception;

	/**
	 * Get the status description in ddbb and the timestamp
	 * 
	 * @param station
	 * @return
	 * @throws Exception
	 */
	public Pair<String, Long> getStatusSeverityDescription(StationsEnum station) throws Exception;

}
