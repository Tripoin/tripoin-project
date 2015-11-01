package com.tripoin.core.dao.base;

import java.io.Serializable;

import javax.persistence.Query;

import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dao.filter.SortArgument;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class JQLStatement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6015568586113220840L;
	
	private static JQLStatement instance;
	
	private JQLStatement() {}
	
	public static JQLStatement getInstance(){
		if(instance == null)
			instance = new JQLStatement();
		return instance;
	}
	
	public Query setParameterStatement(Query query, Object[] values){
		for (int i=0; i<values.length; i++) {
			query.setParameter(i+1, values[i]);
		}
		return query;
	}
	
	public Query pageStatement(Query query, PageArgument pageArgument) throws Exception {
		if(pageArgument.getFirstPage() != null && pageArgument.getFirstPage() > 0)
			query.setFirstResult(pageArgument.getFirstPage());
		if(pageArgument.getMaxPage() != null && pageArgument.getMaxPage() > pageArgument.getFirstPage())
			query.setMaxResults(pageArgument.getMaxPage());				
		return query;
	}

	public <T> String getJQL(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument){
		String jqlQuery = ECommonOperator.FROM.toString().concat(objectType.getName()).concat(ECommonOperator.OBJECT_SPACE_CONSTANT.toString());
		if (filterArguments != null)
			jqlQuery += getOperator(filterArguments);
		
		if (sortArgument != null )
			jqlQuery += getSort(sortArgument);
		
		return jqlQuery;
	}
	
	private String getOperator(FilterArgument[] filterArguments){
		String jqlQuery = ECommonOperator.WHERE.toString();
		boolean isWhere = true;
		for(FilterArgument filterArgument : filterArguments){
			if(isWhere) isWhere = false;
			else
				jqlQuery = jqlQuery.concat(ECommonOperator.AND.toString());
			jqlQuery = jqlQuery.concat(ECommonOperator.OBJECT_POINT_CONSTANT.toString()).concat(filterArgument.getField());
			if(filterArgument.getCondition() == null)
				jqlQuery = jqlQuery.concat(ECommonOperator.EQUALS.toString());
			else
				jqlQuery = jqlQuery.concat(filterArgument.getCondition().toString());
		}		
		return jqlQuery;
	}
	
	private String getSort(SortArgument sortArgument){		
		String jqlQuery = ECommonOperator.SORT.toString();
		boolean isOrder = true;
		for (String field : sortArgument.getField()) {
			if (isOrder) isOrder = false;
			else jqlQuery = jqlQuery.concat(ECommonOperator.COMA_BOTH_SIDE_SPACE.toString());
			jqlQuery = jqlQuery.concat(ECommonOperator.OBJECT_POINT_CONSTANT.toString()).concat(field);
		}
		if(sortArgument.isAsc() == null || sortArgument.isAsc())
			jqlQuery = jqlQuery.concat(ECommonOperator.ASC.toString());
		else
			jqlQuery = jqlQuery.concat(ECommonOperator.DESC.toString());
		return jqlQuery;
	}
	
}
