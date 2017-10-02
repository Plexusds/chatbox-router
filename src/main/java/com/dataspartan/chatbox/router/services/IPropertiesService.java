package com.dataspartan.chatbox.router.services;

public interface IPropertiesService {

	/**
	 * Get message by key
	 * 
	 * @param key
	 * @return
	 */
	public String getMessage(String key);

	/**
	 * Get message by key and parameters
	 * 
	 * @param key
	 * @param parameters
	 * @return
	 */
	public String getMessage(String key, Object[] parameters);

}
