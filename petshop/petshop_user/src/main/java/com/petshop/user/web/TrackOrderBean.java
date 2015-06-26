package com.petshop.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.petshop.model.ejbs.IOrderDetService;
import com.petshop.model.entities.OrderDetail;
import com.petshop.model.entities.User;
import com.petshop.user.utilities.SessionBean;

/**
 * Managed JSF Bean class to browse through all the categories. This class uses
 * ejb to get the data from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "trackOrderBean")
@SessionScoped
public class TrackOrderBean {

	@EJB
	private IOrderDetService orderDetService;

	private OrderDetail orderDetail = new OrderDetail();
	private List<OrderDetail> orderDetList = new ArrayList<OrderDetail>();

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

		HttpSession session = SessionBean.getSession();

		if (null != session.getAttribute("loggedInUser")) {
			this.orderDetList = orderDetService.findOrdersByUser((User) session
					.getAttribute("loggedInUser"));

			if (null != this.orderDetList && this.orderDetList.size() > 0) {

				for (OrderDetail orderTemp : this.orderDetList) {
					System.out
							.println("orderTemp::: " + orderTemp.getOrderNo());

				}
			}
		}

	}

	public String orderStatus() {
		return "/protected/orderDetails";
	}

}
