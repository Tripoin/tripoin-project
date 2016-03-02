package com.tripoin.core.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.dao.base.ABaseDaoJpa;
import com.tripoin.core.dao.filter.ValueArgument;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class GenericDaoJpaImpl extends ABaseDaoJpa {

	@Transactional
	@Override
	public int execQueryNotCriteria(String query, ValueArgument[] valueArguments) throws Exception {
		Query queries = getEntityManager().createNativeQuery(query);
		if (valueArguments != null && valueArguments.length > 0)
			jqlStatement.setParameterStatement(queries, valueArguments);
		int i = queries.executeUpdate();
		return i;
	}

	@Transactional
	@Override
	public int execQueryNotCriteria(String query, Object[] values) throws Exception {
		Query queries = getEntityManager().createNativeQuery(query);
		if (values != null && values.length > 0)
			jqlStatement.setParameterStatement(queries, values);
		int i = queries.executeUpdate();
		return i;
	}

}
