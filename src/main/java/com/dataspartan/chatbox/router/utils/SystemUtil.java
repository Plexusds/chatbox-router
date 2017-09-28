package com.dataspartan.chatbox.router.utils;

import org.apache.log4j.Logger;

public class SystemUtil {

	final static Logger logger = Logger.getLogger(SystemUtil.class);

	/**
	 * Get value form system environment
	 * 
	 * @param key
	 * @return
	 */
	public static String getEnv(String key) {
		return getEnv(key, null);
	}

	/**
	 * Get value form system environment with default value
	 * 
	 * @param key
	 * @return
	 */
	public static String getEnv(String key, String def) {
		String result = null;
		logger.info("Getting env var:" + key);
		result = System.getenv(key);
		if (result == null)
			result = def;
		logger.info("Env var:" + key + " has value " + result);
		return result;
	}

}
