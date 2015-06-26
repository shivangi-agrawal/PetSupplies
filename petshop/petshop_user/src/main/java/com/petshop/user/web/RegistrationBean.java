package com.petshop.user.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.petshop.model.ejbs.IRoleService;
import com.petshop.model.ejbs.IUserService;
import com.petshop.model.entities.Role;
import com.petshop.model.entities.User;
import com.petshop.user.utilities.MessageUtil;
import com.petshop.user.utilities.SessionBean;

/**
 * Managed JSF Bean class to perform user registration. This class uses ejb to
 * set the data into the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "registerBean")
@SessionScoped
public class RegistrationBean {

	private static Logger LOGGER = Logger.getLogger(RegistrationBean.class
			.getName());

	@EJB
	private IUserService userService;

	@EJB
	private IRoleService roleService;

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
	 * Clears the current user bean
	 */
	public void clearBean() {
		this.user = new User();
	}

	/**
	 * This method calls createUser method of UserService to register the new
	 * user in the database
	 * 
	 * @return main to navigate to main.xhtml
	 */
	public String registerUser() {

		try {
			user.setRole(this.role);
			userService.createUser(this.user);

			MessageUtil.addSuccessMessage("User was successfully created.");

			HttpSession session = SessionBean.getSession();
			session.setAttribute("loggedInUser", this.user);

			if (null != session.getAttribute("prodSelected")
					&& !("".equals(session.getAttribute("prodSelected")))
					&& ("true".equals(session.getAttribute("prodSelected")))) {
				session.setAttribute("prodSelected", "false");
				return "/public/browseProd";
			} else {
				return "/protected/main";
			}

		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					"User could not be saved. A Persisting error occured.");
			MessageUtil
					.addErrorMessage("User could not be saved. A Persisting error occured.");
			return "/public/login";
		}
	}

}
