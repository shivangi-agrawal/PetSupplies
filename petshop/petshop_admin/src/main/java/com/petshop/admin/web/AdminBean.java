package com.petshop.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.petshop.admin.utilities.MessageUtil;
import com.petshop.model.ejbs.IRoleService;
import com.petshop.model.ejbs.IUserService;
import com.petshop.model.entities.Role;
import com.petshop.model.entities.User;

/**
 * Managed JSF Bean class to perform all user management. This class uses ejb to
 * get the data from the database
 * 
 * @author shivangi
 *
 */
public class AdminBean {

	private static Logger LOGGER = Logger.getLogger(AdminBean.class.getName());

	@EJB
	private IRoleService roleService;

	@EJB
	private IUserService userService;

	private Role role = new Role();
	private List<Role> roleList = new ArrayList<Role>();

	private User user = new User();
	private List<User> userList = new ArrayList<User>();

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * This method uses ejb to get the role
	 * 
	 * @return Users entity object
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param user
	 *            set the user object
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * This function loads the users.
	 */
	public void loadRoles() {
		this.role = new Role();
		this.roleList = roleService.findAllRoles();
	}

	/**
	 * This function loads the users.
	 */
	public void loadUsers() {
		this.user = new User();
		this.userList = userService.findAllUsers();
	}

	/**
	 * This method calls createUser method of UserService to create the user
	 * Entity
	 * 
	 * @return userList to navigate to userList.xhtml
	 */
	public void createRole() {

		try {
			if (null != this.role) {
				if (this.role.getRoleId() == 1) {
					this.role.setRoleName("admin");

				} else if (this.role.getRoleId() == 2) {
					this.role.setRoleName("user");
				}
			}

			roleService.createRole(this.role);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Role already Exist.");
			MessageUtil.addErrorMessage("Role already Exist.");
		}

	}

	public void createRoles() {

		try {
			roleService.createRoles();
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Role already Exist.");
		}

	}

	/**
	 * This method calls createUser method of UserService to create the user
	 * Entity
	 * 
	 * @return userList to navigate to userList.xhtml
	 */
	public void createUser() {

		try {
			this.user.setRole(new Role(1, "admin"));

			userService.createUser(this.user);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "User already Exist.");
			MessageUtil.addErrorMessage("User already Exist.");
		}

	}

}
