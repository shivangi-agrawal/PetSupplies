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

import com.petshop.model.ejbs.ICategoryService;
import com.petshop.model.entities.Category;

@ManagedBean
@RequestScoped
public class CategoryConverter implements Converter {

	private static Logger LOGGER = Logger.getLogger(CategoryConverter.class
			.getName());

	@EJB
	private ICategoryService categoryService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String submittedVal) {
		if (submittedVal == null || submittedVal.isEmpty()) {
			return null;
		}

		try {
			return categoryService.findCategoryById(Integer
					.parseInt(submittedVal));
		} catch (NumberFormatException e) {
			LOGGER.log(Level.SEVERE,
					String.format("%s is not a valid User ID", submittedVal));
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Category ID", submittedVal)), e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object modelVal) {
		if (modelVal == null) {
			return "";
		}

		if (modelVal instanceof Category) {
			return String.valueOf(((Category) modelVal).getCategoryId());
		} else {
			throw new ConverterException(new FacesMessage(String.format(
					"%s is not a valid Category", modelVal)));
		}

	}

}
