package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.Product;

/**
 * EJB Local bean class IProductService
 * 
 * @author shivangi
 */
@Local
public interface IProductService {

	/**
	 * @return Returns the list of categories
	 */
	List<Product> findAllProducts();

	/**
	 * Finds the category by id
	 * 
	 * @param id
	 * @return returns the Category
	 */
	Product findProductById(int id);

	/**
	 * Creates a category in the database
	 * 
	 * @param category
	 */
	void createProduct(Product product);

	/**
	 * deletes the category from the database
	 * 
	 * @param category
	 */
	void deleteProduct(Product product);

	/**
	 * updates the category in the database
	 * 
	 * @param category
	 */
	void updateProduct(Product product);

	/**
	 * searches the category in the database
	 * 
	 * @param category
	 * @return returns the Category
	 */
	Product searchProducts(Product product);
}
