package com.petshop.admin.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class MessageUtil {

	/**
	 * private constructor to hide the implicit public one.
	 */
	private MessageUtil() {
	}

	/**
	 * 
	 * @param msg
	 *            String to be shown as success message
	 */
	public static void addSuccessMessage(String msg) {

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage("sucessInfo", facesMsg);
	}

	/**
	 * 
	 * @param msg
	 *            String to be shown as error message
	 */
	public static void addErrorMessage(String msg) {

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
}