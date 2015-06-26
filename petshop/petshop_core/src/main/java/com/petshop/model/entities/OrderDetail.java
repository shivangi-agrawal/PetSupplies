package com.petshop.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: OrderDetails
 * 
 * @author shivangi
 */
@Entity
@Table(name = "ORDER_DETAIL")
@NamedQuery(name = "OrderDetail.findOrderByUser", query = "SELECT od FROM OrderDetail od "
		+ "                                                     WHERE od.orderId IN ( select o.orderDetail.orderId from UserOrder o where o.user.id= :id) ")
public class OrderDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5795925431139903503L;

	public static final String FIND_ORDER_BY_USER = "OrderDetail.findOrderByUser";

	private int orderId;
	private String status;
	private String orderNo;
	private BigDecimal totalAmount;
	private Date orderDate;
	private Set<UserOrder> orderses = new HashSet<UserOrder>(0);

	public OrderDetail() {
	}

	public OrderDetail(int orderId, String status, String orderNo,
			BigDecimal totalAmount, int quantity, Date orderDate) {
		this.orderId = orderId;
		this.status = status;
		this.orderNo = orderNo;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
	}

	public OrderDetail(int orderId, String status, String orderNo,
			BigDecimal totalAmount, int quantity, Date orderDate,
			Set<UserOrder> orderses) {
		this.orderId = orderId;
		this.status = status;
		this.orderNo = orderNo;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
		this.orderses = orderses;
	}

	@Id
	@Column(name = "ORDER_ID", unique = true, nullable = false)
	@SequenceGenerator(name = "orderdet_seq", sequenceName = "orderdet_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "orderdet_seq")
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Column(name = "STATUS", nullable = false, length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "ORDER_NO", unique = true, nullable = false, length = 50)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "TOTAL_AMOUNT", nullable = false, precision = 12)
	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE", nullable = false, length = 23)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@OneToMany(mappedBy = "orderDetail", cascade = { CascadeType.PERSIST })
	public Set<UserOrder> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<UserOrder> orderses) {
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
		result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
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
		if (!(obj instanceof OrderDetail)) {
			return false;
		}

		OrderDetail other = (OrderDetail) obj;

		if (orderNo == null) {
			if (other.orderNo != null) {
				return false;
			}
		} else if (!orderNo.equals(other.orderNo)) {
			return false;
		}

		return true;
	}

}
