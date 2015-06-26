package com.petshop.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Users
 * 
 * @author shivangi
 */
@Entity
@Table(name = "USER")
@NamedQueries({
		@NamedQuery(name = "User.validateAdminUser", query = "SELECT u FROM User u "
				+ "                                                     WHERE u.userId = :userId "
				+ "                                                     AND u.password = :password "
				+ "                                                     AND u.role.roleId = :roleId"),
		@NamedQuery(name = "User.validateUser", query = "SELECT u FROM User u "
				+ "                                                     WHERE u.userId = :userId "
				+ "                                                     AND u.password = :password"),
		@NamedQuery(name = "User.searchUser", query = "SELECT u FROM User u"
				+ "												WHERE 	u.userId LIKE :userId "
				+ "														AND u.firstName LIKE :firstName"
				+ "														AND u.lastName LIKE :lastName"
				+ "														AND u.emailId LIKE :emailId"
				+ "														AND u.address LIKE :address"
				+ "														AND u.phoneNumber LIKE :phoneNumber") })
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VALIDATE_ADMIN_USER = "User.validateAdminUser";
	public static final String VALIDATE_USER = "User.validateUser";
	public static final String SEARCH_USER = "User.searchUser";

	private int id;
	private Role role;
	private String firstName;
	private String lastName;
	private String emailId;
	private String userId;
	private String password;
	private String phoneNumber;
	private String address;
	private Set<UserOrder> orderses = new HashSet<UserOrder>(0);

	/**
	 * default constructor
	 */
	public User() {
	}

	/**
	 * constructor
	 * 
	 * @param id
	 * @param role
	 * @param firstName
	 * @param emailId
	 * @param userId
	 * @param phoneNumber
	 * @param address
	 */
	public User(int id, Role role, String firstName, String emailId,
			String userId, String phoneNumber, String address) {
		this.id = id;
		this.role = role;
		this.firstName = firstName;
		this.emailId = emailId;
		this.userId = userId;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	/**
	 * @return the user identifier
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 3, allocationSize = 1)
	@GeneratedValue(generator = "user_seq")
	public int getId() {
		return this.id;
	}

	/**
	 * @param role
	 *            set the user identifier
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return role associated with the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	public Role getRole() {
		return this.role;
	}

	/**
	 * @param roles
	 *            set the user roles
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the first name
	 */
	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName
	 *            set the user first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the last name
	 */
	@Column(name = "LAST_NAME", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName
	 *            set the user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email id
	 */
	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 50)
	public String getEmailId() {
		return this.emailId;
	}

	/**
	 * @param emailId
	 *            set the user email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the user id
	 */
	@Column(name = "USER_ID", unique = true, nullable = false, length = 50)
	public String getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 *            set the user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            set the user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phone number
	 */
	@Column(name = "PHONE_NUMBER", nullable = false, length = 15)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            set the user phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	@Column(name = "ADDRESS", nullable = false)
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param address
	 *            set the user address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the set of all the orders placed by the user
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserOrder> getOrderses() {
		return this.orderses;
	}

	/**
	 * @param orderses
	 *            set all the associated orders in the user entity
	 */
	public void setOrderses(Set<UserOrder> orderses) {
		this.orderses = orderses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof Category)) {
			return false;
		}

		User castOther = (User) other;

		return (this.getEmailId() == castOther.getEmailId() || (this
				.getEmailId() != null && this.getEmailId().equals(
				castOther.getEmailId())))
				&& this.getUserId() == castOther.getUserId()
				|| (this.getUserId() != null && this.getUserId().equals(
						castOther.getUserId()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 37;
		int result = 1;

		result = prime
				* result
				+ ((this.getEmailId() == null) ? 0 : this.getEmailId()
						.hashCode());
		result = prime
				* result
				+ ((this.getUserId() == null) ? 0 : this.getUserId().hashCode());

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", userId=" + userId
				+ ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", orderses=" + orderses + "]";
	}

}
