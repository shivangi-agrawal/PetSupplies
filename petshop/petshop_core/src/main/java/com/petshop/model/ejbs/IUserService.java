package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.User;

/**
 * EJB Local bean class IUserService
 * 
 * @author shivangi
 */
@Local
public interface IUserService {

	/**
	 * @return Returns the list of users
	 */
	List<User> findAllUsers();

	/**
	 * Finds the user by id
	 * 
	 * @param id
	 * @return returns the User
	 */
	User findUserById(int id);

	/**
	 * Creates a user in the database
	 * 
	 * @param user
	 */
	void createUser(User user);

	/**
	 * deletes the user from the database
	 * 
	 * @param user
	 */
	void deleteUser(User user);

	/**
	 * updates the user in the database
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * Validates the user based on username and password
	 * 
	 * @param username
	 * @param password
	 * @return returns the User
	 */
	User validateUser(String username, String password);

	/**
	 * Validates the user based on username, role and password
	 * 
	 * @param username
	 * @param password
	 * @return returns the User
	 */
	User validateUser(String username, String password, int roleId);

	/**
	 * searches the user in the database
	 * 
	 * @param user
	 * @return Returns the list of users
	 */
	List<User> searchUsers(User user);
}
