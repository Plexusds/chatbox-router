package com.dataspartan.chatbox.router.services.impl;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import com.dataspartan.chatbox.router.services.IPropertiesService;

@Service
public class PropertiesServiceImpl implements IPropertiesService {

	private ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	private Locale locale;

	@PostConstruct
	public void init() {
		messageSource.setBasename("Messages");
		locale = new Locale("en", "EN");
	}

	public String getMessage(String key) {
		return this.getMessage(key, null);
	}

	public String getMessage(String key, Object[] parameters) {
		return messageSource.getMessage(key, parameters, locale);
	}

}
