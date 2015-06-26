package com.petshop.model.entities;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Products
 * 
 * @author shivangi
 */
@Entity
@Table(name = "PRODUCTS")
@NamedQuery(name = "Products.findProductByName", query = "select p from Products p where p.productName = :productName")
public class Products implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040436754679122110L;

	public static final String FIND_BY_NAME = "Products.findProductByName";

	private int productId;
	private Categories categories;
	private String productName;
	private String productDesc;
	private BigDecimal amount;
	private Blob image;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	/**
	 * default constructor
	 */
	public Products() {
	}

	/**
	 * constructor
	 * 
	 * @param productId
	 * @param categories
	 * @param productName
	 * @param productDesc
	 * @param amount
	 */
	public Products(int productId, Categories categories, String productName,
			String productDesc, BigDecimal amount) {
		this.productId = productId;
		this.categories = categories;
		this.productName = productName;
		this.productDesc = productDesc;
		this.amount = amount;
	}

	/**
	 * constructor
	 * 
	 * @param productId
	 * @param categories
	 * @param productName
	 * @param productDesc
	 * @param amount
	 * @param image
	 * @param orderses
	 */
	public Products(int productId, Categories categories, String productName,
			String productDesc, BigDecimal amount, Blob image,
			Set<Orders> orderses) {
		this.productId = productId;
		this.categories = categories;
		this.productName = productName;
		this.productDesc = productDesc;
		this.amount = amount;
		this.image = image;
		this.orderses = orderses;
	}

	/**
	 * @return the product id
	 */
	@Id
	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	@SequenceGenerator(name = "prod_seq", sequenceName = "prod_seq", initialValue = 5, allocationSize = 1)
	@GeneratedValue(generator = "prod_seq")
	public int getProductId() {
		return this.productId;
	}

	/**
	 * @param productId
	 *            set the product identifier
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the categories associated with the product
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	public Categories getCategories() {
		return this.categories;
	}

	/**
	 * @param categories
	 *            set the category associated with the product
	 */
	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	/**
	 * @return the product name
	 */
	@Column(name = "PRODUCT_NAME", unique = true, nullable = false, length = 50)
	public String getProductName() {
		return this.productName;
	}

	/**
	 * @param productName
	 *            set the product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the product description
	 */
	@Column(name = "PRODUCT_DESC", length = 1000)
	public String getProductDesc() {
		return this.productDesc;
	}

	/**
	 * @param productDesc
	 *            set the product description
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the product price
	 */
	@Column(name = "AMOUNT", nullable = false, precision = 12)
	public BigDecimal getAmount() {
		return this.amount;
	}

	/**
	 * @param amount
	 *            set the product price
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the product image
	 */
	@Column(name = "IMAGE")
	public Blob getImage() {
		return this.image;
	}

	/**
	 * @param image
	 *            set the product image
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

	/**
	 * @return the order placed for the product
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "products")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	/**
	 * @param orderses
	 *            set the product order details
	 */
	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
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
				+ ((productName == null) ? 0 : productName.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}

		Products other = (Products) obj;
		if (productName == null) {
			if (other.productName != null) {
				return false;
			}
		} else if (!productName.equals(other.productName)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "Products [productName=" + productName + ", productDesc="
				+ productDesc + ", amount=" + amount + ", image=" + image
				+ ", orderses=" + orderses + "]";
	}

}
