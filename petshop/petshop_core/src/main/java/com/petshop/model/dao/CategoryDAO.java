package com.petshop.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.Category;

/**
 * DAO implementation class for entity Categories
 * 
 * @author shivangi
 */
@Stateless
public class CategoryDAO extends GenericDAO<Category> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public CategoryDAO() {
		super(Category.class);
	}

	/**
	 * searches the category in the database
	 * 
	 * @param category
	 *            contains the criteria for search
	 * @return the list of Categories
	 */
	public Category searchCategory(Category category) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (null != category.getCategoryName()
				&& !category.getCategoryName().isEmpty()) {
			parameters.put(Constants.CATEGORY_NAME, category.getCategoryName());
		} else {
			parameters.put(Constants.CATEGORY_NAME, "%");
		}

		return super.findOneResult(Category.FIND_BY_NAME, parameters);
	}

}