package com.tripoin.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.dao.base.ABaseDeleteDaoJpa;
import com.tripoin.core.dao.util.IVersionControlSystemTableDao;
import com.tripoin.core.pojo.IBaseModel;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class GenericDeleteDaoJpaImpl extends ABaseDeleteDaoJpa {
	
	@Autowired
	private IVersionControlSystemTableDao iVersionControlSystemTableDao;
	
	@Autowired
	@Qualifier(value="transactionManager")
	private PlatformTransactionManager transactionManager ;
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;
	
	private Object objectType;

	public void deleteObjectAndSync(final Object objectType) throws Exception {
		deleteObject(objectType);
		this.objectType = objectType;
		taskExecutor.execute(new Runnable() {			
			@Override
			public void run() {
				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
				transactionTemplate.execute(new TransactionCallback<Object>() {
					@Override
					public Object doInTransaction(TransactionStatus arg0) {
						try {
							iVersionControlSystemTableDao.insertValueAndSync(((IBaseModel)objectType).tableName(), new Long(1), "Table of ".concat(((IBaseModel)objectType).tableName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
			}
		});		
	}

	@Override
	public void deleteObjectListAndSync(List<Object> objectTypeList) throws Exception {
		for(Object objectType : objectTypeList)
			deleteObject(objectType);
		this.objectType = objectTypeList.get(0);
		taskExecutor.execute(new Runnable() {			
			@Override
			public void run() {
				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
				transactionTemplate.execute(new TransactionCallback<Object>() {
					@Override
					public Object doInTransaction(TransactionStatus arg0) {
						try {
							iVersionControlSystemTableDao.insertValueAndSync(((IBaseModel)objectType).tableName(), new Long(1), "Table of ".concat(((IBaseModel)objectType).tableName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				});
			}
		});		
	}

}
