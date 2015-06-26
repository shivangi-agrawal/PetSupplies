package com.petshop.model.entities;

import javax.persistence.Column;

/**
 * Entity implementation class for : OrdersId
 * 
 * @author shivangi
 */

public class OrdersId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3722035615014893187L;
	private int orderId;
	private int userId;
	private int productId;

	public OrdersId() {
	}

	public OrdersId(int orderId, int userId, int productId) {
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
	}

	@Column(name = "ORDER_ID", nullable = false)
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Column(name = "USER_ID", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "PRODUCT_ID", nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof OrdersId)) {
			return false;
		}

		OrdersId castOther = (OrdersId) other;

		return (this.getOrderId() == castOther.getOrderId())
				&& (this.getUserId() == castOther.getUserId())
				&& (this.getProductId() == castOther.getProductId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getOrderId();
		result = 37 * result + this.getUserId();
		result = 37 * result + this.getProductId();
		return result;
	}

}
