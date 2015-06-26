package com.petshop.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import com.petshop.model.common.Constants;
import com.petshop.model.entities.User;

/**
 * DAO implementation class for entity Users
 * 
 * @author shivangi
 */
@Stateless
public class UserDAO extends GenericDAO<User> {

	private static Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	/**
	 * default constructor This is setting entity class in generic DAO
	 */
	public UserDAO() {
		super(User.class);
	}

	/**
	 * validates the user in the database
	 * 
	 * @param username
	 * @param password
	 * @return returns the validated user
	 */
	public User validateUser(String username, String password) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put(Constants.USER_ID, username);
		parameters.put(Constants.USER_PASSWORD, password);

		return super.findOneResult(User.VALIDATE_USER, parameters);
	}

	/**
	 * validates the user in the database
	 * 
	 * @param username
	 * @param password
	 * @param roleId
	 * @return returns the validated user
	 */
	public User validateUser(String username, String password, int roleId) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put(Constants.USER_ID, username);
		parameters.put(Constants.USER_PASSWORD, password);
		parameters.put(Constants.ROLE_ID, roleId);

		return super.findOneResult(User.VALIDATE_ADMIN_USER, parameters);
	}

	/**
	 * searches the user in the database
	 * 
	 * @param user
	 *            object contains the criteria for search
	 * @return returns the list of Users
	 */
	public List<User> searchUser(User user) {

		try {
			Map<String, Object> parameters = new HashMap<String, Object>();

			if (null != user.getUserId() && !user.getUserId().isEmpty()) {
				parameters.put(Constants.USER_ID, user.getUserId());
			} else {
				parameters.put(Constants.USER_ID, "%");
			}

			if (null != user.getFirstName() && !user.getFirstName().isEmpty()) {
				parameters.put(Constants.USER_FIRST_NAME, user.getFirstName());
			} else {
				parameters.put(Constants.USER_FIRST_NAME, "%");
			}

			if (null != user.getLastName() && !user.getLastName().isEmpty()) {
				parameters.put(Constants.USER_LAST_NAME, user.getLastName());
			} else {
				parameters.put(Constants.USER_LAST_NAME, "%");
			}

			if (null != user.getEmailId() && !user.getEmailId().isEmpty()) {
				parameters.put(Constants.USER_EMAIL_ID, user.getEmailId());
			} else {
				parameters.put(Constants.USER_EMAIL_ID, "%");
			}

			if (null != user.getAddress() && !user.getAddress().isEmpty()) {
				parameters.put(Constants.USER_ADDRESS, user.getAddress());
			} else {
				parameters.put(Constants.USER_ADDRESS, "%");
			}

			if (null != user.getPhoneNumber()
					&& !user.getPhoneNumber().isEmpty()) {
				parameters.put(Constants.USER_PHONE_NUMBER,
						user.getPhoneNumber());
			} else {
				parameters.put(Constants.USER_PHONE_NUMBER, "%");
			}

			return super.findResults(User.SEARCH_USER, parameters);

		} catch (NoResultException nre) {
			LOGGER.log(Level.INFO, Constants.MSG_SRCH_ERR);
		}

		return new ArrayList<User>();
	}

}