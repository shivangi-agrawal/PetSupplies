package com.petshop.admin.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.petshop.admin.message.MessageProvider;
import com.petshop.admin.utilities.MessageUtil;
import com.petshop.admin.utilities.SessionBean;
import com.petshop.model.ejbs.IUserService;
import com.petshop.model.entities.User;

/**
 * Managed JSF Bean class to perform login. This class uses ejb to get the data
 * from the database
 * 
 * @author shivangi
 *
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1094801825228386363L;

	@EJB
	private IUserService userService;

	private User user;

	private String password;
	private String username;

	/**
	 * This method uses ejb to get the user
	 * 
	 * @return Users entity object
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @return User password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param pwd
	 *            set the user input password
	 */
	public void setPassword(String pwd) {
		this.password = pwd;
	}

	/**
	 * @return User name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            set the user input name
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * This method validates the username and password and if validated then
	 * puts the user object in session
	 * 
	 * @return if validated returns main to navigate to main.xhtml
	 */
	public String login() {

		User currentUser = userService.validateUser(this.getUsername(),
				this.getPassword(), 1);

		if (currentUser != null) {
			this.user = currentUser;

			HttpSession session = SessionBean.getSession();
			session.setAttribute("loggedInUser", currentUser);

			return "/protected/common/main";
		} else {
			MessageUtil.addErrorMessage(new MessageProvider()
					.getValue("login.failed"));

			return "/public/login";
		}

	}

	/**
	 * This method invalidates the session
	 * 
	 * @return login to navigate to login.xhtml
	 */
	public String logout() {
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).invalidate();

		return "/public/login";
	}

}
