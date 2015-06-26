package com.petshop.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Orders
 * 
 * @author shivangi
 */
@Entity
@Table(name = "ORDERS")
@NamedQuery(name = "Orders.findOrdersByUserId", query = "select o from Orders o where o.users= :user")
public class Orders implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8531080331817911452L;

	public static final String SEARCH_ORDER_BY_USER = "Orders.findOrdersByUserId";

	private int id;
	private OrderDetails orderDetails;
	private Products products;
	private User users;
	private int quantity;

	public Orders() {
	}

	public Orders(int id, OrderDetails orderDetails, Products products,
			User users) {
		this.id = id;
		this.orderDetails = orderDetails;
		this.products = products;
		this.users = users;
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
	public OrderDetails getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUsers() {
		return this.users;
	}

	public void setUsers(User users) {
		this.users = users;
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
