package com.petshop.user.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petshop.model.entities.User;

/**
 * Filter checks if session has loggedInUser attribute set to the current user.
 * If it is not set then request is being redirected to the login.xhml page.
 *
 * @author shivangi
 *
 */
public class LoginFilter implements Filter {

	/**
	 * This method checks if user is logged in. If not it redirects to the
	 * login.xhtml page.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @param chain
	 *            filter chain
	 * @throws IOException
	 *             if an I/O error occurs.
	 * @throws ServletException
	 *             if a servlet-specific error occurs.
	 */
	public final void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		User user = (User) ((HttpServletRequest) request).getSession()
				.getAttribute("loggedInUser");

		if (user == null) {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/public/login.xhtml");
		} else {
			HttpServletResponse httpsResponse = (HttpServletResponse) response;
			// HTTP 1.1.
			httpsResponse.setHeader("Cache-Control",
					"no-cache, no-store, must-revalidate");
			// HTTP 1.0.
			httpsResponse.setHeader("Pragma", "no-cache");
			// Proxies.
			httpsResponse.setDateHeader("Expires", 0);

			chain.doFilter(request, response);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(final FilterConfig config) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

}
