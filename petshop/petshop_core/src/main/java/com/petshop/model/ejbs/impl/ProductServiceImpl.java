package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.ProductDAO;
import com.petshop.model.ejbs.IProductService;
import com.petshop.model.entities.Product;

/**
 * Session Bean implementation class IProductService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class ProductServiceImpl implements IProductService {

	@EJB
	private ProductDAO productDAO;

	/**
	 * Default constructor.
	 */
	public ProductServiceImpl() {
	}

	@Override
	public List<Product> findAllProducts() {
		return productDAO.findAll();
	}

	@Override
	public Product findProductById(int id) {
		return productDAO.find(id);
	}

	@Override
	public void createProduct(Product product) {
		productDAO.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.update(product);
	}

	@Override
	public Product searchProducts(Product product) {
		return productDAO.searchProduct(product);
	}

}
