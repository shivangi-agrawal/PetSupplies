package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.Role;

/**
 * EJB Local bean class IRoleService
 * 
 * @author shivangi
 */
@Local
public interface IRoleService {

	/**
	 * @return Returns the list of roles
	 */
	List<Role> findAllRoles();

	/**
	 * Finds the role by id
	 * 
	 * @param id
	 * @return returns the Role
	 */
	Role findRoleById(int id);

	/**
	 * Creates the role
	 * 
	 * @param role
	 *            object
	 */
	void createRole(Role role);

	void createRoles();

}
