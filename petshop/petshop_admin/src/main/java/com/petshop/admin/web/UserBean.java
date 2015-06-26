package com.petshop.admin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.petshop.admin.message.MessageProvider;
import com.petshop.admin.utilities.MessageUtil;
import com.petshop.admin.utilities.SessionBean;
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
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	private static Logger LOGGER = Logger.getLogger(UserBean.class.getName());

	@EJB
	private IUserService userService;

	@EJB
	private IRoleService roleService;

	private List<User> userList = new ArrayList<User>();
	private User user = new User();
	private Role role;

	/**
	 * This method uses ejb to get the user
	 * 
	 * @return Users entity object
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            set the user object
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This method gets called once dependency injection is done It uses ejb to
	 * get the role
	 */
	@PostConstruct
	public void init() {
		this.role = roleService.findRoleById(2);
	}

	/**
	 * This method uses ejb to get the list of the users
	 * 
	 * @return returns the user list
	 */
	public List<User> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            set the list of the users
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * This function loads the users.
	 */
	public void loadUsers() {
		this.userList = userService.findAllUsers();
	}

	/**
	 * Finds the user by id.
	 */
	public void findUser() {
		this.user = userService.findUserById(this.user.getId());
	}

	/**
	 * Clears the current user bean
	 */
	public void clearUser() {
		this.user = new User();
	}

	/**
	 * This method calls deleteUser method of UserService to delete the user
	 * Entity
	 * 
	 * @param user
	 *            object to be deleted
	 * @return userList to navigate to userList.xhtml
	 */
	public String deleteUser(User user) {
		try {
			userService.deleteUser(user);
			MessageUtil.addSuccessMessage(new MessageProvider()
					.getValue("user.delete"));
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Could not delete User.");
			MessageUtil.addErrorMessage("Could not delete User.");
		}

		return "/protected/user/userList";
	}

	/**
	 * This method calls createUser method of UserService to create the user
	 * Entity
	 * 
	 * @return userList to navigate to userList.xhtml
	 */
	public String createUser() {

		try {
			user.setRole(this.role);
			userService.createUser(this.user);
			MessageUtil.addSuccessMessage("Post was successfully created.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Post could not be saved. "
					+ "A Persisting error occured.");
			MessageUtil.addErrorMessage("Post could not be saved. "
					+ "A Persisting error occured.");
		}

		return "/protected/user/userList";
	}

	/**
	 * This method calls updateUser method of UserService to update the user
	 * Entity
	 * 
	 * @return viewUser to navigate to viewUser.xhtml page
	 */
	public String updateUser() {

		try {
			userService.updateUser(this.user);
			MessageUtil.addSuccessMessage("User \"" + this.user.getUserId()
					+ "\" was successfully updated.");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "User \"" + this.user.getId()
					+ "\" could not be saved. An update error occured.");
			MessageUtil.addErrorMessage("User \"" + this.user.getId()
					+ "\" could not be saved. An update error occured.");
		}

		return "/protected/user/viewUser";
	}

	/**
	 * This method calls searchUser method of UserService to search the user
	 * Entity based on input
	 * 
	 * @return userResult to navigate to userResult.xhtml page
	 */
	public String searchUser() {

		try {
			this.userList = userService.searchUsers(this.user);
			return "/protected/user/userResult";
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Search is not available right now"
					+ ". Please search after sometime.");
			MessageUtil.addErrorMessage("Search is not available right now"
					+ ". Please search after sometime.");
			return "/protected/user/searchUser";
		}
	}

	/**
	 * Finds the user by id.
	 */
	public void loadLoginUser() {
		HttpSession session = SessionBean.getSession();
		this.user = (User) session.getAttribute("loggedInUser");
	}

	/**
	 * This method calls updateUser method of UserService to update the user
	 * Entity
	 * 
	 * @return viewUser to navigate to viewUser.xhtml page
	 */
	public String updateProfile() {

		try {
			userService.updateUser(this.user);
			MessageUtil
					.addSuccessMessage("Profile updated successfully. Please relogin to view the changes.");

			((HttpSession) FacesContext.getCurrentInstance()
					.getExternalContext().getSession(false)).invalidate();

			return "/public/login";
		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					"Profile could not be saved. An update error occured.");
			MessageUtil
					.addErrorMessage("Profile could not be saved. An update error occured.");
			return "/protected/common/updateProfile";
		}
	}

}
