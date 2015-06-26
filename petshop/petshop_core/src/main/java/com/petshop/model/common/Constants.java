package com.petshop.model.common;

/**
 * Collected constants of core module.
 * <P>
 * All members of this class are immutable.
 */
public final class Constants {

	/**
	 * userid attribute of Users entity class
	 */
	public static final String ID = "id";
	/**
	 * userid attribute of Users entity class
	 */
	public static final String USER_ID = "userId";
	/**
	 * password attribute of Users entity class
	 */
	public static final String USER_PASSWORD = "password";
	/**
	 * firstName attribute of Users entity class
	 */
	public static final String USER_FIRST_NAME = "firstName";
	/**
	 * lastName attribute of Users entity class
	 */
	public static final String USER_LAST_NAME = "lastName";
	/**
	 * emailId attribute of Users entity class
	 */
	public static final String USER_EMAIL_ID = "emailId";
	/**
	 * address attribute of Users entity class
	 */
	public static final String USER_ADDRESS = "address";
	/**
	 * phoneNumber attribute of Users entity class
	 */
	public static final String USER_PHONE_NUMBER = "phoneNumber";
	/**
	 * roleid attribute of Roles entity class
	 */
	public static final String ROLE_ID = "roleId";
	/**
	 * categoryName attribute of Roles entity class
	 */
	public static final String CATEGORY_NAME = "categoryName";
	/**
	 * productName attribute of Roles entity class
	 */
	public static final String PRODUCT_NAME = "productName";
	/**
	 * persistent unit name to connect to the database
	 */
	public static final String UNIT_NAME = "primary";
	/**
	 * persistent unit name to connect to the database
	 */
	public static final String MSG_SRCH_ERR = "Can not find search result: ";
	/**
	 * user attribute of orders entity class
	 */
	public static final String ORDER_USER = "user";

	public static final String ROLE_USER = "user";

	public static final String ROLE_ADMIN = "admin";

	/**
	 * Caller should be prevented from constructing objects of this class, by
	 * declaring this private constructor.
	 */
	private Constants() {
	}

}