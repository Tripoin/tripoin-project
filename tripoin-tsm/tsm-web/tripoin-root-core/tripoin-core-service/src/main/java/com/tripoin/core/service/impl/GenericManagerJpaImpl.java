package com.tripoin.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dao.IGenericDeleteDaoJpa;
import com.tripoin.core.dao.IGenericInsertDaoJpa;
import com.tripoin.core.dao.IGenericReadDaoJpa;
import com.tripoin.core.dao.IGenericUpdateDaoJpa;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dao.filter.SortArgument;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("iGenericManagerJpa")
public class GenericManagerJpaImpl implements IGenericManagerJpa {

	@Autowired
	private IGenericDeleteDaoJpa genericDeleteDao;
	@Autowired
	private IGenericInsertDaoJpa genericInsertDao;
	@Autowired
	private IGenericReadDaoJpa genericReadDao;
	@Autowired
	private IGenericUpdateDaoJpa genericUpdateDao;

	@Override
	public <T> T loadObjectFilterKey(Class<T> objectType, Serializable key) throws Exception {
		return genericReadDao.loadObjectFilterKey(objectType, key);
	}

	@Override
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception {
		return genericReadDao.loadObjects(objectType);
	}

	@Override
	public <T> List<T> loadObjectsFilterArgument(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument, PageArgument pageArgument) throws Exception {
		return genericReadDao.loadObjectsFilterArgument(objectType, filterArguments, values, sortArgument, pageArgument);
	}

	@Override
	public <T> List<T> loadObjectsJQLStatement(String jql, Object[] values, PageArgument pageArgument) throws Exception {
		return genericReadDao.loadObjectsJQLStatement(jql, values, pageArgument);
	}

	@Override
	public void saveObject(Object objectType) throws Exception {
		genericInsertDao.saveObject(objectType);
	}

	@Override
	public void updateObject(Object objectType) throws Exception {
		genericUpdateDao.updateObject(objectType);
	}

	@Override
	public void deleteObject(Object objectType) throws Exception {
		genericDeleteDao.deleteObject(objectType);		
	}

}
