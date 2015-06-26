package com.petshop.model.ejbs;

import java.util.List;

import javax.ejb.Local;

import com.petshop.model.entities.Status;

/**
 * EJB Local bean class IRoleService
 * 
 * @author shivangi
 */
@Local
public interface IStatusService {

	/**
	 * @return Returns the list of status
	 */
	List<Status> findAllStatus();

	public Status findStatusById(int id);

	void createStatus(Status status);

}
