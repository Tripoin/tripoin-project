package com.tripoin.core.dao;

import com.tripoin.core.dao.filter.ValueArgument;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IGenericDaoJpa {
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>ValueArgument[] valueArguments = new ValueArgument[]{</code><br>
	 * <code>new ValueArgument("name", "jakarta"),</code><br>
	 * <code>new ValueArgument("code", "JAKARTA")};</code><br>
	 * <code>int i = iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_area SET area_name = :name WHERE area_code = :code", valueArguments);</code><br>
	 * @param query
	 * @param valueArguments
	 * @return
	 * @throws Exception
	 */
	public int execQueryNotCriteria(String query, ValueArgument[] valueArguments) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>Object[] values = new Object[]{</code><br>
	 * <code>new Object("jakarta"),</code><br>
	 * <code>new Object("JAKARTA")};</code><br>
	 * <code>int i = iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_area SET area_name = ? WHERE area_code = ?", values);</code><br>
	 * @param query
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public int execQueryNotCriteria(String query, Object[] values) throws Exception;
	
}