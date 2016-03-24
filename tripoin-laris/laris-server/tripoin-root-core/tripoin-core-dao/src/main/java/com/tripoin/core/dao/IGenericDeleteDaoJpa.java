package com.tripoin.core.dao;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IGenericDeleteDaoJpa {
		
	/**
	 * <b>Sample Code:</b><br>
	 * <code>deleteObject(user)</code><br>
	 * @param objectType
	 * @throws Exception
	 */
	public void deleteObject(Object objectType) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>deleteObjectAndSync(user)</code><br><br>
	 * delete data and update vcs_table for count data current table<br><br>
	 * <b>WARNING : <i>Make It Slower</i></b>
	 * @param objectType
	 * @throws Exception
	 */
	public void deleteObjectAndSync(final Object objectType) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>deleteObjectListAndSync(users)</code><br><br>
	 * delete data and update vcs_table for count data current table<br><br>
	 * <b>WARNING : <i>Make It Slower</i></b>
	 * @param objectTypeList
	 * @throws Exception
	 */
	public void deleteObjectListAndSync(final List<Object> objectTypeList) throws Exception;	
	
}