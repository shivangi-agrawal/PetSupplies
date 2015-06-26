package com.petshop.user.web;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.petshop.model.ejbs.IUserService;
import com.petshop.model.entities.User;
import com.petshop.user.utilities.MessageUtil;
import com.petshop.user.utilities.SessionBean;

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

	private User user = new User();
	
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
	 * Finds the user by id.
	 */
	public void loadUser() {
		HttpSession session = SessionBean.getSession();
		this.user = (User)session.getAttribute("loggedInUser");
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
			MessageUtil.addSuccessMessage("Profile updated successfully. Please relogin to view the changes.");
			
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false)).invalidate();
			
			return "/public/login";
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Profile could not be saved. An update error occured.");
			MessageUtil.addErrorMessage("Profile could not be saved. An update error occured.");
			return "/protected/updateProfile";
		}

		
	}

}
