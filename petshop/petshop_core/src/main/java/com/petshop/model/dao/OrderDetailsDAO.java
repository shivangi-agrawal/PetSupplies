package com.petshop.model.dao;

import javax.ejb.Stateless;

import com.petshop.model.entities.OrderDetails;

/**
 * DAO implementation class for entity Categories
 * 
 * @author shivangi
 */
@Stateless
public class OrderDetailsDAO extends GenericDAO<OrderDetails> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public OrderDetailsDAO() {
		super(OrderDetails.class);
	}

}