package com.petshop.user.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

public class MessageProvider {

	private static Logger LOGGER = Logger.getLogger(MessageProvider.class
			.getName());

	private ResourceBundle bundle;

	public ResourceBundle getBundle() {

		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msg");
		}

		return bundle;
	}

	public String getValue(String key) {

		String result = null;

		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			LOGGER.log(Level.INFO, "???" + key + "??? not found");
			result = "???" + key + "??? not found";
		}

		return result;
	}

}