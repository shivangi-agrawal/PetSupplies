package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.OrderDetail;
import com.petshop.model.entities.User;

/**
 * EJB Local bean class ICategoryService
 * 
 * @author shivangi
 */
@Local
public interface IOrderDetService {

	/**
	 * @return Returns the list of order
	 */
	List<OrderDetail> findAllOrders();

	/**
	 * Finds the order details by id
	 * 
	 * @param id
	 * @return returns the OrderDetails
	 */
	OrderDetail findOrderById(int id);

	/**
	 * updates the order status in the database
	 * 
	 * @param orderDet
	 */
	void updateStatus(OrderDetail orderDet);

	/**
	 * Creates a order in the database
	 * 
	 * @param orderDet
	 */
	void createOrder(OrderDetail orderDet);

	List<OrderDetail> findOrdersByUser(User user);

}
