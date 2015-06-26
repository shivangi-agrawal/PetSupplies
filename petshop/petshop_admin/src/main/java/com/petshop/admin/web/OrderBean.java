package com.petshop.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.petshop.admin.utilities.MessageUtil;
import com.petshop.model.common.Status;
import com.petshop.model.ejbs.IOrderDetService;
import com.petshop.model.entities.OrderDetail;

/**
 * Managed JSF Bean class to browse through all the categories. This class uses
 * ejb to get the data from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "orderBean")
@SessionScoped
public class OrderBean {

	private static Logger LOGGER = Logger.getLogger(OrderBean.class.getName());

	@EJB
	private IOrderDetService orderDetService;

	private OrderDetail orderDetail = new OrderDetail();
	private OrderDetail orderDetailInit = new OrderDetail();
	private List<OrderDetail> orderDetList = new ArrayList<OrderDetail>();
	private List<OrderDetail> initOrderList = new ArrayList<OrderDetail>();

	/**
	 * @return the orderDetailInit
	 */
	public OrderDetail getOrderDetailInit() {
		return orderDetailInit;
	}

	/**
	 * @param orderDetailInit
	 *            the orderDetailInit to set
	 */
	public void setOrderDetailInit(OrderDetail orderDetailInit) {
		this.orderDetailInit = orderDetailInit;
	}

	/**
	 * @return the initOrderList
	 */
	public List<OrderDetail> getInitOrderList() {
		return initOrderList;
	}

	/**
	 * @param initOrderList
	 *            the initOrderList to set
	 */
	public void setInitOrderList(List<OrderDetail> initOrderList) {
		this.initOrderList = initOrderList;
	}

	/**
	 * @return the orderDetList
	 */
	public List<OrderDetail> getOrderDetList() {
		return orderDetList;
	}

	/**
	 * @param orderDetList
	 *            the orderDetList to set
	 */
	public void setOrderDetList(List<OrderDetail> orderDetList) {
		this.orderDetList = orderDetList;
	}

	/**
	 * @return the orderDetails
	 */
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	/**
	 * @param orderDetails
	 *            the orderDetails to set
	 */
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	@PostConstruct
	public void init() {
		this.orderDetList = orderDetService.findAllOrders();

		if (null != this.orderDetList && this.orderDetList.size() > 0) {

			for (OrderDetail orderTemp : this.orderDetList) {
				if (orderTemp.getStatus().trim()
						.equals(Status.SUBMITTED.toString())) {
					this.initOrderList.add(orderTemp);
				}

			}
		}

	}

	public String orderStatus() {
		return "/protected/order/orderStatus";
	}

	public String updateStatus() {
		return "/protected/order/updateOrder";
	}

	public String changeStatus(String status) {

		try {
			if (null != this.orderDetailInit) {

				if (null != status && !("".equals(status))) {
					if (status.trim().equals("approve")) {
						this.orderDetailInit.setStatus((Status.APPROVED)
								.toString());
					} else if (status.trim().equals("reject")) {
						this.orderDetailInit.setStatus((Status.REJECTED)
								.toString());
					}
				}

				orderDetService.updateStatus(this.orderDetailInit);
			}
			MessageUtil.addSuccessMessage("Status updated succesfully.");

		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Post could not be saved. "
					+ "A Persisting error occured.");
			MessageUtil.addErrorMessage("Post could not be saved. "
					+ "A Persisting error occured.");
		}

		return "/protected/order/orderStatus";
	}
}
