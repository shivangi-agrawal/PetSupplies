package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.Category;

/**
 * EJB Local bean class ICategoryService
 * 
 * @author shivangi
 */
@Local
public interface ICategoryService {

	/**
	 * @return Returns the list of categories
	 */
	List<Category> findAllCategories();

	/**
	 * Finds the category by id
	 * 
	 * @param id
	 * @return returns the Category
	 */
	Category findCategoryById(int id);

	/**
	 * Creates a category in the database
	 * 
	 * @param category
	 */
	void createCategory(Category category);

	/**
	 * deletes the category from the database
	 * 
	 * @param category
	 */
	void deleteCategory(Category category);

	/**
	 * updates the category in the database
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * searches the category in the database
	 * 
	 * @param category
	 * @return returns the Category
	 */
	Category searchCategory(Category category);
}
