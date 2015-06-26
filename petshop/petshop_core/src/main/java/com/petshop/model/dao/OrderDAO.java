package com.petshop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.OrderDetails;
import com.petshop.model.entities.Orders;
import com.petshop.model.entities.User;

/**
 * DAO implementation class for entity Categories
 * 
 * @author shivangi
 */
@Stateless
public class OrderDAO extends GenericDAO<Orders> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public OrderDAO() {
		super(Orders.class);
	}

	public void createOrder(List<Orders> orderList, OrderDetails orderDetails) {

		for (Orders order : orderList) {
			// order.setId(new OrdersId(orderDetails.getOrderId(), order
			// .getUsers().getId(), order.getProducts().getProductId()));
			super.save(order);
			System.out.println("saveddddddddddddddd:: " + order.getId());
		}

	}

	/**
	 * searches the order in the database
	 * 
	 * @param product
	 *            contains the criteria for search
	 * @return returns the list of Products
	 */
	public List<Orders> findOrdersByUserId(User user) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (user != null) {
			parameters.put(Constants.ORDERS_USER, user);
			return super.findResults(Orders.SEARCH_ORDER_BY_USER, parameters);
		}

		return null;

	}

}