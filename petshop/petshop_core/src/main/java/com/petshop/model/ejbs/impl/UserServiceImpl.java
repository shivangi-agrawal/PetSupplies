package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.UserDAO;
import com.petshop.model.ejbs.IUserService;
import com.petshop.model.entities.User;

/**
 * Session Bean implementation class IUserService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class UserServiceImpl implements IUserService {

	@EJB
	private UserDAO userDAO;

	/**
	 * Default constructor.
	 */
	public UserServiceImpl() {
	}

	@Override
	public List<User> findAllUsers() {
		return userDAO.findAll();
	}

	@Override
	public User findUserById(int id) {
		return userDAO.find(id);
	}

	@Override
	public void createUser(User user) {
		userDAO.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDAO.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDAO.delete(user);
	}

	@Override
	public User validateUser(String username, String password) {
		return userDAO.validateUser(username, password);
	}

	@Override
	public User validateUser(String username, String password, int roleId) {
		return userDAO.validateUser(username, password, roleId);
	}

	@Override
	public List<User> searchUsers(User user) {
		return userDAO.searchUser(user);
	}

}
