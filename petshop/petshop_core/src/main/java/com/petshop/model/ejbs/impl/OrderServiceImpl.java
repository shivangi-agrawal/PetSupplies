package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.OrderDAO;
import com.petshop.model.ejbs.IOrderService;
import com.petshop.model.entities.OrderDetails;
import com.petshop.model.entities.Orders;
import com.petshop.model.entities.User;

/**
 * Session Bean implementation class ICategoryService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class OrderServiceImpl implements IOrderService {

	@EJB
	private OrderDAO orderDAO;

	/**
	 * Default constructor.
	 */
	public OrderServiceImpl() {
	}

	@Override
	public void createOrder(List<Orders> orderList, OrderDetails orderDetails) {
		orderDAO.createOrder(orderList, orderDetails);
	}

	@Override
	public List<Orders> findAllOrders() {
		return orderDAO.findAll();
	}

	@Override
	public List<Orders> findOrdersByUserId(User user) {
		return orderDAO.findOrdersByUserId(user);
	}

	@Override
	public Orders findOrderById(int id) {
		return orderDAO.find(id);
	}

}
