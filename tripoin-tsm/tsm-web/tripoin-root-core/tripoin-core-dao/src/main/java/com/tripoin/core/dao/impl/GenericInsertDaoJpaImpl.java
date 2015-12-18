package com.tripoin.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tripoin.core.dao.base.ABaseInsertDaoJpa;
import com.tripoin.core.dao.util.IVersionControlSystemTableDao;
import com.tripoin.core.pojo.IBaseModel;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class GenericInsertDaoJpaImpl extends ABaseInsertDaoJpa {
	
	@Autowired
	private IVersionControlSystemTableDao iVersionControlSystemTableDao;
	
	public void saveObjectAndSync(Object objectType) throws Exception {
		saveObject(objectType);
		iVersionControlSystemTableDao.insertValueAndSync(((IBaseModel)objectType).tableName(), new Long(1), "Table of ".concat(((IBaseModel)objectType).tableName()));
	}

}
