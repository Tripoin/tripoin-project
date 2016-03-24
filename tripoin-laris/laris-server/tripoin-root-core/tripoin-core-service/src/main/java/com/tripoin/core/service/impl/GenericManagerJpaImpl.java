package com.tripoin.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.dao.IGenericDaoJpa;
import com.tripoin.core.dao.IGenericDeleteDaoJpa;
import com.tripoin.core.dao.IGenericInsertDaoJpa;
import com.tripoin.core.dao.IGenericReadDaoJpa;
import com.tripoin.core.dao.IGenericUpdateDaoJpa;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dao.filter.SortArgument;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("iGenericManagerJpa")
public class GenericManagerJpaImpl implements IGenericManagerJpa {

	@Autowired
	private IGenericDaoJpa genericDao;
	@Autowired
	private IGenericReadDaoJpa genericReadDao;
	@Autowired
	private IGenericInsertDaoJpa genericInsertDao;
	@Autowired
	private IGenericUpdateDaoJpa genericUpdateDao;
	@Autowired
	private IGenericDeleteDaoJpa genericDeleteDao;

    @Transactional
	@Override
	public <T> T loadObjectFilterKey(Class<T> objectType, Serializable key) throws Exception {
		return genericReadDao.loadObjectFilterKey(objectType, key);
	}

    @Transactional
	@Override
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception {
		return genericReadDao.loadObjects(objectType);
	}

    @Transactional
	@Override
	public <T> List<T> loadObjectsFilterArgument(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument, PageArgument pageArgument) throws Exception {
		return genericReadDao.loadObjectsFilterArgument(objectType, filterArguments, values, sortArgument, pageArgument);
	}

    @Transactional
	@Override
	public <T> List<T> loadObjectsJQLStatement(String jql, Object[] values, PageArgument pageArgument) throws Exception {
		return genericReadDao.loadObjectsJQLStatement(jql, values, pageArgument);
	}

    @Transactional
	@Override
	public <T> Long totalRowData(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument, PageArgument pageArgument) throws Exception {
    	return genericReadDao.totalRowData(objectType, filterArguments, values, sortArgument, pageArgument);
	}

    @Transactional
	@Override
	public Object getObjectSQLNative(String sql, FilterArgument[] filterArguments, Object[] values) throws Exception {
		return genericReadDao.getObjectSQLNative(sql, filterArguments, values);
	}

    @Transactional
	@Override
	public Object getObjectSQLNative(String sql, Object[] values) throws Exception {
		return genericReadDao.getObjectSQLNative(sql, values);
	}

    @Transactional
	@Override
	public void saveObject(Object objectType) throws Exception {
		genericInsertDao.saveObject(objectType);
	}

    @Transactional
	@Override
	public void saveObjectAndSync(final Object objectType) throws Exception {
		genericInsertDao.saveObjectAndSync(objectType);
	}

    @Transactional
	@Override
	public void updateObject(Object objectType) throws Exception {
		genericUpdateDao.updateObject(objectType);
	}

    @Transactional
	@Override
	public void deleteObject(Object objectType) throws Exception {
		genericDeleteDao.deleteObject(objectType);		
	}

    @Transactional
	@Override
	public void deleteObjectAndSync(Object objectType) throws Exception {
		genericDeleteDao.deleteObjectAndSync(objectType);
	}

    @Transactional
	@Override
	public void deleteObjectListAndSync(List<Object> objectTypeList) throws Exception {
		genericDeleteDao.deleteObjectListAndSync(objectTypeList);
	}

    @Transactional
	@Override
	public int execQueryNotCriteria(String query, ValueArgument[] valueArguments) throws Exception {
		return genericDao.execQueryNotCriteria(query, valueArguments);
	}

    @Transactional
	@Override
	public int execQueryNotCriteria(String query, Object[] values) throws Exception {
		return genericDao.execQueryNotCriteria(query, values);
	}

}
