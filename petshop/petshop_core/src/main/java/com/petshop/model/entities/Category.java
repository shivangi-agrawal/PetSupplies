package com.petshop.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: CATEGORIES
 * 
 * @author shivangi
 */
@Entity
@Table(name = "CATEGORY")
@NamedQuery(name = "Category.findCategoryByName", query = "select c from Category c where c.categoryName = :categoryName")
public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8722379816232884432L;

	public static final String FIND_BY_NAME = "Category.findCategoryByName";

	private int categoryId;
	private String categoryName;
	private String categoryDesc;
	private Set<Product> productses = new HashSet<Product>(0);

	/**
	 * default constructor
	 */
	public Category() {
	}

	/**
	 * constructor
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @param categoryDesc
	 */
	public Category(int categoryId, String categoryName, String categoryDesc) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}

	/**
	 * constructor
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @param categoryDesc
	 * @param productses
	 */
	public Category(int categoryId, String categoryName, String categoryDesc,
			Set<Product> productses) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.productses = productses;
	}

	/**
	 * @return the category id
	 */
	@Id
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	@SequenceGenerator(name = "cat_seq", sequenceName = "cat_seq", allocationSize = 1)
	@GeneratedValue(generator = "cat_seq")
	public int getCategoryId() {
		return this.categoryId;
	}

	/**
	 * @param categoryDesc
	 *            set the category id
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the category name
	 */
	@Column(name = "CATEGORY_NAME", nullable = false, length = 50, unique = true)
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * @param categoryDesc
	 *            set the category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the category description
	 */
	@Column(name = "CATEGORY_DESC")
	public String getCategoryDesc() {
		return this.categoryDesc;
	}

	/**
	 * @param categoryDesc
	 *            set the category description
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return the set of products associated with the category
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "category")
	public Set<Product> getProductses() {
		return this.productses;
	}

	/**
	 * @param productses
	 *            set all the associated products
	 */
	public void setProductses(Set<Product> productses) {
		this.productses = productses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Category)) {
			return false;
		}

		Category other = (Category) obj;

		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		return true;
	}

}
