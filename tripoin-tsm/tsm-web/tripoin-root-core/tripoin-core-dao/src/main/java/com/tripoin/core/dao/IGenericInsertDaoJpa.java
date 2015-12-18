package com.tripoin.core.dao;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IGenericInsertDaoJpa {
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>saveObject(user)</code><br>
	 * @param objectType
	 * @throws Exception
	 */
	public void saveObject(Object objectType) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>saveObjectAndSync(user)</code><br><br>
	 * insert data and update vcs_table for count data current table<br><br>
	 * <b>WARNING : <i>Make It Slower</i></b>
	 * @param objectType
	 * @throws Exception
	 */
	public void saveObjectAndSync(Object objectType) throws Exception;	
	
}