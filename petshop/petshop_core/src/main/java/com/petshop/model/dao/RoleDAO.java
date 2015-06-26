package com.petshop.model.dao;

import javax.ejb.Stateless;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.Role;

/**
 * DAO implementation class for entity Roles
 * 
 * @author shivangi
 */
@Stateless
public class RoleDAO extends GenericDAO<Role> {

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public RoleDAO() {
		super(Role.class);
	}

	public void createRoles() {
		Role adminRole = new Role(1, Constants.ROLE_ADMIN);
		Role userRole = new Role(2, Constants.ROLE_USER);

		super.save(adminRole);
		super.save(userRole);
	}
}