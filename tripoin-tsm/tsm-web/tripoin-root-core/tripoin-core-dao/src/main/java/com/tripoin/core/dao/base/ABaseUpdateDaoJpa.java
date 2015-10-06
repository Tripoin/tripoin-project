package com.tripoin.core.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.dao.IGenericUpdateDaoJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseUpdateDaoJpa implements IGenericUpdateDaoJpa {

    @PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	 
    protected EntityManager getEntityManager() {
        return entityManager;
    }

	@Override
	@Transactional
	public void updateObject(Object objectType) throws Exception {
		getEntityManager().merge(objectType);
		getEntityManager().flush();
	}
    
}