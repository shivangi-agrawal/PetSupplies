package com.petshop.model.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.Product;

/**
 * DAO implementation class for entity Products
 * 
 * @author shivangi
 */
@Stateless
public class ProductDAO extends GenericDAO<Product> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public ProductDAO() {
		super(Product.class);
	}

	/**
	 * searches the product in the database
	 * 
	 * @param product
	 *            contains the criteria for search
	 * @return returns the list of Products
	 */
	public Product searchProduct(Product product) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (null != product.getProductName()
				&& !product.getProductName().isEmpty()) {
			parameters.put(Constants.PRODUCT_NAME, product.getProductName());
		} else {
			parameters.put(Constants.PRODUCT_NAME, "%");
		}

		return super.findOneResult(Product.FIND_BY_NAME, parameters);
	}

}