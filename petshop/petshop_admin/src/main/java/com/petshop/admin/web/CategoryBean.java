package com.petshop.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.petshop.admin.utilities.MessageUtil;
import com.petshop.model.ejbs.ICategoryService;
import com.petshop.model.entities.Category;

/**
 * Managed JSF Bean class to perform all categories management. This class uses
 * ejb to get the data from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean {

	private static Logger LOGGER = Logger.getLogger(CategoryBean.class
			.getName());

	@EJB
	private ICategoryService categoryService;

	private Category category = new Category();
	private List<Category> categoryList = new ArrayList<Category>();

	/**
	 * This method uses ejb to get the category
	 * 
	 * @return Categories entity object
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            set the categories object
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * This method uses ejb to get the list of the categories
	 * 
	 * @return returns the category list
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            set the list of the categories
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * This function loads the categories.
	 */
	public void loadCategories() {
		this.categoryList = categoryService.findAllCategories();
	}

	/**
	 * Finds the category by id.
	 */
	public void findCategory() {
		this.category = categoryService.findCategoryById(this.category
				.getCategoryId());
	}

	/**
	 * Clears the current category bean
	 */
	public void clearCategory() {
		this.category = new Category();
	}

	/**
	 * This method calls deleteCategory method of CategoryService to delete the
	 * category Entity
	 * 
	 * @param category
	 *            object to be deleted
	 * @return categoryList to navigate to categoryList.xhtml
	 */
	public String deleteCategory(Category category) {

		try {
			categoryService.deleteCategory(category);
			MessageUtil.addSuccessMessage("Post was successfully deleted.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Could not delete Category.");
			MessageUtil.addErrorMessage("Could not delete Category.");
		}

		return "/protected/category/categoryList";
	}

	/**
	 * This method calls createCategory method of CategoryService to create the
	 * category Entity
	 * 
	 * @return categoryList to navigate to categoryList.xhtml
	 */
	public String createCategory() {

		try {
			categoryService.createCategory(this.category);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Post could not be saved. "
					+ "A Persisting error occured.");
			MessageUtil.addErrorMessage("Post could not be saved. "
					+ "A Persisting error occured.");
		}

		return "/protected/category/categoryList";
	}

	/**
	 * This method calls updateCategory method of CategoryService to update the
	 * category Entity
	 * 
	 * @return viewCategory to navigate to viewCategory.xhtml page
	 */
	public String updateCategory() {

		try {
			categoryService.updateCategory(this.category);
			MessageUtil.addSuccessMessage("Category "
					+ this.category.getCategoryId()
					+ " was successfully updated.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Category " + this.category.getCategoryId()
					+ " could not be saved. An update error occured.");
			MessageUtil.addErrorMessage("Category "
					+ this.category.getCategoryId()
					+ " could not be saved. An update error occured.");
		}

		return "/protected/category/viewCategory";
	}

	/**
	 * This method calls searchCategory method of CategoryService to search the
	 * category Entity based on user input
	 * 
	 * @return categoryResult to navigate to categoryResult.xhtml page
	 */
	public String searchCategory() {

		try {
			this.category = categoryService.searchCategory(this.category);
			return "/protected/category/categoryResult";
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Search is not available right now. "
					+ "Please search after sometime.");
			MessageUtil.addErrorMessage("Search is not available right now. "
					+ "Please search after sometime.");
			return "/protected/category/searchCategory";
		}
	}

}
