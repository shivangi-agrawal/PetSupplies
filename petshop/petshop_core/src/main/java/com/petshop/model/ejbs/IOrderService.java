package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.OrderDetails;
import com.petshop.model.entities.Orders;
import com.petshop.model.entities.User;

/**
 * EJB Local bean class ICategoryService
 * 
 * @author shivangi
 */
@Local
public interface IOrderService {

	/**
	 * creates order
	 * 
	 * @param orderDetails
	 * @param orderList
	 */
	void createOrder(List<Orders> orderList, OrderDetails orderDetails);

	/**
	 * @return Returns the list of order
	 */
	List<Orders> findAllOrders();

	/**
	 * Finds the order by userid
	 * 
	 * @param id
	 * @return returns the Category
	 */
	List<Orders> findOrdersByUserId(User user);

	/**
	 * Finds the order by id
	 * 
	 * @param id
	 * @return returns the order
	 */
	Orders findOrderById(int id);

}
