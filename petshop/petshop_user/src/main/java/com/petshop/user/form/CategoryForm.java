package com.petshop.user.form;

import java.util.ArrayList;
import java.util.List;

public class CategoryForm {

	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	private int categoryId;
	private String categoryName;
	private String categoryDesc;
	private List<ProductForm> prodSet = new ArrayList<ProductForm>(0);

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryDesc
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * @param categoryDesc
	 *            the categoryDesc to set
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return the prodSet
	 */
	public List<ProductForm> getProdSet() {
		return prodSet;
	}

	/**
	 * @param prodSet
	 *            the prodSet to set
	 */
	public void setProdSet(List<ProductForm> prodSet) {
		this.prodSet = prodSet;
	}

}
