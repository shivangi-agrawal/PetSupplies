package com.petshop.model.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import com.petshop.model.common.Constants;

/**
 * Generic DAO class containing common db transaction methods
 * 
 * @author shivangi
 */
public abstract class GenericDAO<T> {

	private static Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());

	@PersistenceContext(unitName = Constants.UNIT_NAME, type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	private Class<T> entityClass;

	/**
	 * constructor This is setting entity class type
	 */
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * saves the entity in the database
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		em.persist(entity);
	}

	/**
	 * deletes the entity in the database
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		T entityToBeRemoved = em.merge(entity);

		em.remove(entityToBeRemoved);
	}

	/**
	 * updtaes the entity in the database
	 * 
	 * @param entity
	 * @return entity object
	 */
	public T update(T entity) {
		return em.merge(entity);
	}

	/**
	 * finds the entity in the database based on the identifier
	 * 
	 * @param entity
	 * @return entity object
	 */
	public T find(int entityID) {
		em.clear();
		return em.find(entityClass, entityID);
	}

	/**
	 * finds all the values of an entity from the database Using the unchecked
	 * because JPA does not have a em.getCriteriaBuilder().createQuery()<T>
	 * method
	 * 
	 * @return list of all the entity
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	/**
	 * find single result from the database Using the unchecked because JPA does
	 * not have a query.getSingleResult()<T> method
	 * 
	 * @param namedQuery
	 *            String contains the query to be executed
	 * @param parameters
	 *            Map containing the query criteria
	 * @return the entity object
	 */
	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					Constants.MSG_SRCH_ERR + this.entityClass.getName());
		}

		return result;
	}

	/**
	 * find multiple results from the database Using the unchecked because JPA
	 * does not have a query.getSingleResult()<T> method
	 * 
	 * @param namedQuery
	 *            String contains the query to be executed
	 * @param parameters
	 *            Map containing the query criteria
	 * @return the list of entity object
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findResults(String namedQuery,
			Map<String, Object> parameters) {
		List<T> results = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			results = query.getResultList();

		} catch (Exception e) {
			LOGGER.log(Level.INFO,
					Constants.MSG_SRCH_ERR + this.entityClass.getName());
		}

		return results;
	}

	/**
	 * It will populate parameters Using the unchecked because JPA does not have
	 * a query.getSingleResult()<T> method
	 * 
	 * @param query
	 *            contains the query to be executed
	 * @param parameters
	 *            Map containing the query criteria
	 */
	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
}