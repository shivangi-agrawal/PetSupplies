package com.petshop.user.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderForm {

	private List<CategoryForm> catList = new ArrayList<CategoryForm>();
	private List<ProductForm> prodList = new ArrayList<ProductForm>();
	private double totalAmt = 0.0;
	private Date orderDate;
	private String orderNum;
	private int totalQuantity = 0;
	private int statusId = 1;

	/**
	 * @return the prodList
	 */
	public List<ProductForm> getProdList() {
		return prodList;
	}

	/**
	 * @param prodList
	 *            the prodList to set
	 */
	public void setProdList(List<ProductForm> prodList) {
		this.prodList = prodList;
	}

	/**
	 * @return the prodList
	 */
	public List<CategoryForm> getCatList() {
		return catList;
	}

	/**
	 * @param prodList
	 *            the prodList to set
	 */
	public void setCatList(List<CategoryForm> catList) {
		this.catList = catList;
	}

	/**
	 * @return the totalAmt
	 */
	public double getTotalAmt() {
		return totalAmt;
	}

	/**
	 * @param totalAmt
	 *            the totalAmt to set
	 */
	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 *            the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the totalQuantity
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}

	/**
	 * @param totalQuantity
	 *            the totalQuantity to set
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

}
