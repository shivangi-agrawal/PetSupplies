package com.petshop.admin.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.petshop.model.ejbs.IStatusService;
import com.petshop.model.entities.Status;

@ManagedBean
@RequestScoped
public class StatusConverter implements Converter {

	private static Logger LOGGER = Logger.getLogger(StatusConverter.class
			.getName());

	@EJB
	private IStatusService statusService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedVal) {
		if (submittedVal == null || submittedVal.isEmpty()) {
			return null;
		}

		try {
			return statusService.findStatusById(Integer.parseInt(submittedVal));
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE,
					String.format("%s is not a valid User ID", submittedVal));
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid User ID", submittedVal)), e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object modelVal) {
		if (modelVal == null) {
			return "";
		}

		if (modelVal instanceof Status) {
			return String.valueOf(((Status) modelVal).getStatusId());
		} else {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid User", modelVal)));
		}

	}

}
