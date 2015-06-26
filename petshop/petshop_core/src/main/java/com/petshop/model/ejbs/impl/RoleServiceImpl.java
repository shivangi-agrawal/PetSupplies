package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.RoleDAO;
import com.petshop.model.ejbs.IRoleService;
import com.petshop.model.entities.Role;

/**
 * Session Bean implementation class IRoleService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class RoleServiceImpl implements IRoleService {

	@EJB
	private RoleDAO roleDAO;

	/**
	 * Default constructor.
	 */
	public RoleServiceImpl() {
	}

	@Override
	public List<Role> findAllRoles() {
		return roleDAO.findAll();
	}

	@Override
	public Role findRoleById(int id) {
		return roleDAO.find(id);
	}

	@Override
	public void createRole(Role role) {
		roleDAO.save(role);
	}

	@Override
	public void createRoles() {
		roleDAO.createRoles();
	}

}
