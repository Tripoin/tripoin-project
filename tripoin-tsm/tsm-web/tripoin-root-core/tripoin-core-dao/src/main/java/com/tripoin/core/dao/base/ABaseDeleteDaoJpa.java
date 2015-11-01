package com.tripoin.core.dao.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import com.tripoin.core.dao.IGenericDeleteDaoJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public abstract class ABaseDeleteDaoJpa implements IGenericDeleteDaoJpa {

    @PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	 
    protected EntityManager getEntityManager() {
        return entityManager;
    }    

	@Override		
	public void deleteObject(Object objectType) throws Exception {    	
		objectType = entityManager.merge(objectType);
		getEntityManager().remove(objectType);		
	}
    
}