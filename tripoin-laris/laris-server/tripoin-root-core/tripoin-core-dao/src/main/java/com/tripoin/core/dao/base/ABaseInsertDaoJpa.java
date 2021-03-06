package com.tripoin.core.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import com.tripoin.core.dao.IGenericInsertDaoJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseInsertDaoJpa implements IGenericInsertDaoJpa {

    @PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	 
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
	@Override
	public void saveObject(Object objectType) throws Exception {
		getEntityManager().merge(objectType);
		getEntityManager().flush();
	}
    
}