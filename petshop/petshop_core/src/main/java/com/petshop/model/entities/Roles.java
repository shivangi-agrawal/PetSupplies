package com.petshop.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Roles
 * 
 * @author shivangi
 */
@Entity
@Table(name = "ROLES")
@NamedQuery(name = "Roles.findRoleByName", query = "select r from Roles r where r.roleName = :roleName")
public class Roles implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3545984740801474579L;

	public static final String FIND_BY_NAME = "Roles.findRoleByName";

	private int roleId;
	private String roleName;
	private Set<User> userses = new HashSet<User>(0);

	/**
	 * default constructor
	 */
	public Roles() {
	}

	/**
	 * constructor
	 * 
	 * @param roleId
	 * @param roleName
	 */
	public Roles(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	/**
	 * constructor
	 * 
	 * @param roleId
	 * @param roleName
	 * @param userses
	 */
	public Roles(int roleId, String roleName, Set<User> userses) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.userses = userses;
	}

	/**
	 * @return the role id
	 */
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	/**
	 * @param roleId
	 *            set role id
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the role name
	 */
	@Column(name = "ROLE_NAME", unique = true, nullable = false, length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * @param roleName
	 *            set role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the set of users having same role
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUserses() {
		return this.userses;
	}

	/**
	 * @param userses
	 *            set users having same role
	 */
	public void setUserses(Set<User> userses) {
		this.userses = userses;
	}

}
