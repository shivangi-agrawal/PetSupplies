package com.petshop.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.OrderDetail;
import com.petshop.model.entities.User;

/**
 * DAO implementation class for entity Categories
 * 
 * @author shivangi
 */
@Stateless
public class OrderDetailDAO extends GenericDAO<OrderDetail> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public OrderDetailDAO() {
		super(OrderDetail.class);
	}

	public List<OrderDetail> findOrdersByUser(User user) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		List<OrderDetail> result = new ArrayList<OrderDetail>();

		if (null != user) {
			parameters.put(Constants.ID, user.getId());

			result = super.findResults(OrderDetail.FIND_ORDER_BY_USER,
					parameters);

		}

		return result;

	}

}