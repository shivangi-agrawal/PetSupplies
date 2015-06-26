package com.petshop.admin.listener;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MySystemListener
 *
 */
@WebListener
public class MySystemListener implements SystemEventListener {

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		System.out.println("started:::::::::::::::::::");

	}

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof Application;
	}

}
