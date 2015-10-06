package com.tripoin.core.dao.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.common.ECommonOperator;
import com.tripoin.core.dao.IGenericReadDaoJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseReadDaoJpa implements IGenericReadDaoJpa {
	
	protected JQLStatement jqlStatement = JQLStatement.getInstance();

	@PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	 
    protected EntityManager getEntityManager() {
        return entityManager;
    }	
	
	@Override
	@Transactional
	public <T> T loadObjectFilterKey(Class<T> objectType, Serializable key) throws Exception {
		return getEntityManager().find(objectType, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception {
		String jqlQuery = ECommonOperator.FROM.toString().concat(objectType.getName());
		Query query = getEntityManager().createQuery(jqlQuery);
	    return query.getResultList();
	}	
    
}