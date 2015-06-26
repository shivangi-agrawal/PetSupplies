package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.OrderDetailDAO;
import com.petshop.model.ejbs.IOrderDetService;
import com.petshop.model.entities.OrderDetail;
import com.petshop.model.entities.User;

/**
 * Session Bean implementation class ICategoryService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class OrderDetServiceImpl implements IOrderDetService {

	@EJB
	private OrderDetailDAO orderDetailsDAO;

	/**
	 * Default constructor.
	 */
	public OrderDetServiceImpl() {
	}

	@Override
	public OrderDetail findOrderById(int id) {
		return orderDetailsDAO.find(id);
	}

	@Override
	public List<OrderDetail> findOrdersByUser(User user) {
		return orderDetailsDAO.findOrdersByUser(user);
	}

	@Override
	public void updateStatus(OrderDetail orderDet) {
		orderDetailsDAO.update(orderDet);
	}

	@Override
	public void createOrder(OrderDetail orderDet) {
		orderDetailsDAO.save(orderDet);
	}

	@Override
	public List<OrderDetail> findAllOrders() {
		return orderDetailsDAO.findAll();
	}

}
