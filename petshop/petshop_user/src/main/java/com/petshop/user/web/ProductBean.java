package com.petshop.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.petshop.model.ejbs.IProductService;
import com.petshop.model.entities.Product;

@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean {
	
	@EJB
	private IProductService productService;	
	
	private Product product = new Product();
	private List<Product> productList = new ArrayList<Product>();

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * This method uses ejb to get the list of the products
	 * @return returns the category list
	 */
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	
	/**
	 * This function loads the products.
	 */
	public void loadProducts() {
		this.productList = productService.findAllProducts();
	}
	
}
	