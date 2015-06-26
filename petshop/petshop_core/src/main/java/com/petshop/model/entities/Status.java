package com.petshop.model.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Status
 * 
 * @author shivangi
 */
@Entity
@Table(name = "STATUS")
public class Status implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7182268603752358211L;
	private int statusId;
	private String statusDesc;
	private Set<OrderDetails> orderDetailses = new HashSet<OrderDetails>(0);

	public Status() {
	}

	public Status(int statusId, String statusDesc) {
		this.statusId = statusId;
		this.statusDesc = statusDesc;
	}

	public Status(int statusId, String statusDesc,
			Set<OrderDetails> orderDetailses) {
		this.statusId = statusId;
		this.statusDesc = statusDesc;
		this.orderDetailses = orderDetailses;
	}

	@Id
	@Column(name = "STATUS_ID", unique = true, nullable = false)
	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Column(name = "STATUS_DESC", unique = true, nullable = false, length = 20)
	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
	public Set<OrderDetails> getOrderDetailses() {
		return this.orderDetailses;
	}

	public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
		this.orderDetailses = orderDetailses;
	}

}
