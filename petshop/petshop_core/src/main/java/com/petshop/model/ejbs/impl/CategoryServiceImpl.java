package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.CategoryDAO;
import com.petshop.model.ejbs.ICategoryService;
import com.petshop.model.entities.Category;

/**
 * Session Bean implementation class ICategoryService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class CategoryServiceImpl implements ICategoryService {

	@EJB
	private CategoryDAO categoryDAO;

	/**
	 * Default constructor.
	 */
	public CategoryServiceImpl() {
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDAO.findAll();

	}

	@Override
	public Category findCategoryById(int id) {
		return categoryDAO.find(id);
	}

	@Override
	public void createCategory(Category category) {
		categoryDAO.save(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDAO.delete(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDAO.update(category);
	}

	@Override
	public Category searchCategory(Category category) {
		return categoryDAO.searchCategory(category);
	}

}
