package com.petshop.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Orders
 * 
 * @author shivangi
 */
@Entity
@Table(name = "USERORDER")
public class UserOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531080331817911452L;

	private int id;
	private OrderDetail orderDetail;
	private Product product;
	private User user;
	private int quantity;

	public UserOrder() {
	}

	public UserOrder(int id, OrderDetail orderDetail, Product product, User user) {
		this.id = id;
		this.orderDetail = orderDetail;
		this.product = product;
		this.user = user;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name = "order_seq", sequenceName = "order_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "order_seq")
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public OrderDetail getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the quantity
	 */
	@Column(name = "QUANTITY", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
