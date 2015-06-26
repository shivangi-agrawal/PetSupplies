package com.petshop.model.dao;

import javax.ejb.Stateless;

import com.petshop.model.entities.Status;

/**
 * DAO implementation class for entity Roles
 * 
 * @author shivangi
 */
@Stateless
public class StatusDAO extends GenericDAO<Status> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public StatusDAO() {
		super(Status.class);
	}

}