package com.petshop.model.ejbs.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.petshop.model.dao.StatusDAO;
import com.petshop.model.ejbs.IStatusService;
import com.petshop.model.entities.Status;

/**
 * Session Bean implementation class IRoleService
 * 
 * @author shivangi
 */
@Stateless
@LocalBean
public class StatusServiceImpl implements IStatusService {

	@EJB
	private StatusDAO statusDAO;

	/**
	 * Default constructor.
	 */
	public StatusServiceImpl() {
	}

	@Override
	public Status findStatusById(int id) {
		return statusDAO.find(id);
	}

	@Override
	public List<Status> findAllStatus() {
		return statusDAO.findAll();
	}

	@Override
	public void createStatus(Status status) {
		statusDAO.save(status);
	}

}
