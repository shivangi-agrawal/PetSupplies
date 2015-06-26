package com.petshop.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.petshop.admin.utilities.MessageUtil;
import com.petshop.model.ejbs.ICategoryService;
import com.petshop.model.ejbs.IProductService;
import com.petshop.model.entities.Category;
import com.petshop.model.entities.Product;

/**
 * Managed JSF Bean class to perform all Product management. This class uses ejb
 * to get the data from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "productBean")
@SessionScoped
public class ProductBean {

	private static Logger LOGGER = Logger
			.getLogger(ProductBean.class.getName());

	@EJB
	private IProductService productService;

	@EJB
	private ICategoryService categoryService;

	private Product product = new Product();
	private List<Product> productList = new ArrayList<Product>();
	private List<Category> categoryList = new ArrayList<Category>();

	/**
	 * @return the categoryList
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            the categoryList to set
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * This method gets called once dependency injection is done It uses ejb to
	 * get the categories
	 */
	@PostConstruct
	public void init() {
		this.categoryList = categoryService.findAllCategories();
	}

	/**
	 * This method uses ejb to get the product
	 * 
	 * @return Products entity object
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            set the Products object
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * This method uses ejb to get the list of the Products
	 * 
	 * @return returns the product list
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            set the list of the Products
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	/**
	 * This function loads the Products.
	 */
	public void loadProducts() {
		this.productList = productService.findAllProducts();
	}

	/**
	 * Finds the product by id.
	 */
	public void findProduct() {
		this.product = productService.findProductById(this.product
				.getProductId());
	}

	/**
	 * Clears the current product bean
	 */
	public void clearProduct() {
		this.product = new Product();
	}

	/**
	 * This method calls deleteProduct method of ProductService to delete the
	 * product Entity
	 * 
	 * @param product
	 *            object to be deleted
	 * @return productList to navigate to productList.xhtml
	 */
	public String deleteProduct(Product product) {

		try {
			productService.deleteProduct(product);
			MessageUtil.addSuccessMessage("Post was successfully deleted.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Could not delete Product.");
			MessageUtil.addErrorMessage("Could not delete Product.");
		}

		return "/protected/product/productList";
	}

	/**
	 * This method calls createProduct method of ProductService to create the
	 * product Entity
	 * 
	 * @return productList to navigate to productList.xhtml
	 */
	public String createProduct() {

		try {
			productService.createProduct(this.product);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Post could not be saved. "
					+ "A Persisting error occured.");
			MessageUtil.addErrorMessage("Post could not be saved. "
					+ "A Persisting error occured.");
		}

		return "/protected/product/productList";
	}

	/**
	 * This method calls updateProduct method of ProductService to update the
	 * product Entity
	 * 
	 * @return viewProduct to navigate to viewProduct.xhtml page
	 */
	public String updateProduct() {

		try {
			productService.updateProduct(this.product);
			MessageUtil.addSuccessMessage("Product "
					+ this.product.getProductId()
					+ " was successfully updated.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Product " + this.product.getProductId()
					+ " could not be saved. An update error occured.");
			MessageUtil.addErrorMessage("Product "
					+ this.product.getProductId()
					+ " could not be saved. An update error occured.");
		}

		return "/protected/product/viewProduct";
	}

	/**
	 * This method calls searchProduct method of ProductService to search the
	 * product Entity based on user input
	 * 
	 * @return productResult to navigate to productResult.xhtml page
	 */
	public String searchProducts() {

		try {
			this.product = productService.searchProducts(this.product);
			return "/protected/product/productResult";
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Search is not available right now."
					+ " Please search after sometime.");
			MessageUtil.addErrorMessage("Search is not available right now."
					+ " Please search after sometime.");
			return "/protected/product/searchproduct";
		}
	}

}
