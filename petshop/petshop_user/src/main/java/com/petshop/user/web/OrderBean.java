package com.petshop.user.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.petshop.model.common.Status;
import com.petshop.model.ejbs.IOrderDetService;
import com.petshop.model.ejbs.IProductService;
import com.petshop.model.entities.OrderDetail;
import com.petshop.model.entities.User;
import com.petshop.model.entities.UserOrder;
import com.petshop.user.form.OrderForm;
import com.petshop.user.form.ProductForm;
import com.petshop.user.utilities.MessageUtil;
import com.petshop.user.utilities.RandomString;
import com.petshop.user.utilities.SessionBean;

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

	@EJB
	private IOrderDetService orderDetService;

	@EJB
	private IProductService prodService;

	private List<UserOrder> orderList = new ArrayList<UserOrder>();
	private OrderDetail orderDetails = new OrderDetail();

	/**
	 * @return the orderList
	 */
	public List<UserOrder> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList
	 *            the orderList to set
	 */
	public void setOrderList(List<UserOrder> orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the orderDetails
	 */
	public OrderDetail getOrderDetails() {
		return orderDetails;
	}

	/**
	 * @param orderDetails
	 *            the orderDetails to set
	 */
	public void setOrderDetails(OrderDetail orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String createOrder(OrderForm orderForm) {

		HttpSession session = SessionBean.getSession();

		if (null != session.getAttribute("orderPlaced")
				&& !("".equals(session.getAttribute("orderPlaced")))
				&& ("true".equals(session.getAttribute("orderPlaced")))) {
			session.setAttribute("orderPlaced", null);

			return "/public/browseProd";

		} else {

			try {

				if (null != orderForm.getProdList()
						&& orderForm.getProdList().size() > 0) {

					this.orderList = new ArrayList<UserOrder>();
					this.orderDetails = new OrderDetail();

					this.orderDetails.setOrderDate(new Date());
					this.orderDetails.setOrderNo(RandomString
							.randomAlphaNumeric(15));
					this.orderDetails.setStatus(Status.SUBMITTED.toString());
					this.orderDetails.setTotalAmount(BigDecimal
							.valueOf(orderForm.getTotalAmt()));

					for (ProductForm prodTemp : orderForm.getProdList()) {
						UserOrder order = new UserOrder();

						order.setOrderDetail(this.orderDetails);
						order.setUser((User) (session
								.getAttribute("loggedInUser")));

						order.setProduct(prodService.findProductById(prodTemp
								.getProductId()));
						order.setQuantity(prodTemp.getQuantity());

						this.orderList.add(order);
					}

					this.orderDetails.setOrderses(new HashSet<UserOrder>(
							this.orderList));

					orderDetService.createOrder(this.orderDetails);

					session.setAttribute("orderPlaced", "true");

				}

				MessageUtil.addSuccessMessage("Order submitted successfully.");

			} catch (Exception e) {

			}
			return "/protected/orderStatus";
		}

	}

}
