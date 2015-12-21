package com.tripoin.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
	
	@Autowired
	@Qualifier(value="transactionManager")
	private PlatformTransactionManager transactionManager ;
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor; 

	public void saveObjectAndSync(final Object objectType) throws Exception {
		saveObject(objectType);
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
