package com.petshop.model.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ORDER_DETAILS")
public class OrderDetails implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5795925431139903503L;
	private int orderId;
	private Status status;
	private String orderNo;
	private BigDecimal totalAmount;
	private Date orderDate;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public OrderDetails() {
	}

	public OrderDetails(int orderId, Status status, String orderNo,
			BigDecimal totalAmount, int quantity, Date orderDate) {
		this.orderId = orderId;
		this.status = status;
		this.orderNo = orderNo;
		this.totalAmount = totalAmount;
		this.orderDate = orderDate;
	}

	public OrderDetails(int orderId, Status status, String orderNo,
			BigDecimal totalAmount, int quantity, Date orderDate,
			Set<Orders> orderses) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
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

	@OneToMany(mappedBy = "orderDetails", cascade = { CascadeType.PERSIST })
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
