package com.tripoin.core.dao;

import java.io.Serializable;
import java.util.List;

import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dao.filter.SortArgument;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IGenericReadDaoJpa {
	
	/** 
	 * <b>Sample Code:</b><br>
	 * <code>loadObjectFilterKey(User.class, id)</code><br>
	 * @param objectType
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public <T> T loadObjectFilterKey(Class<T> objectType, Serializable key) throws Exception ;
	
	/**
	 * <b>Sample Code</b><br>
	 * <code>loadObjects(User.class)</code><br>
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>FilterArgument[] filterArguments = new FilterArgument[]{</code><br>
	 * <code>&emsp;&emsp;new FilterArgument("username", ECommonOperator.LIKE_BOTH_SIDE)</code><br>
	 * <code>}</code><br>
	 * <code>loadObjectsFilterArgument(User.class, filterArguments, new Object[]{value}, new SortArgument(new String[]{"username"}, false), new PageArgument(0,2))</code><br>
	 * @param objectType
	 * @param filterArguments
	 * @param values
	 * @param sortArgument
	 * @param pageArgument
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> loadObjectsFilterArgument(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument, PageArgument pageArgument) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{value}, new PageArgument(0,2))</code><br>
	 * @param jql
	 * @param values
	 * @param pageArgument
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> loadObjectsJQLStatement(String jql, Object[] values, PageArgument pageArgument) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>FilterArgument[] filterArguments = new FilterArgument[]{</code><br>
	 * <code>&emsp;&emsp;new FilterArgument("username", ECommonOperator.LIKE_BOTH_SIDE)</code><br>
	 * <code>}</code><br>
	 * <code>totalRowData(User.class, filterArguments, new Object[]{value}, new SortArgument(new String[]{"username"}, false), new PageArgument(0,2))</code><br>
	 * @param objectType
	 * @param filterArguments
	 * @param values
	 * @param sortArgument
	 * @param pageArgument
	 * @return
	 * @throws Exception
	 */
	public <T> Long totalRowData(Class<T> objectType, FilterArgument[] filterArguments, Object[] values, SortArgument sortArgument, PageArgument pageArgument) throws Exception;
	
	/**
	 * <b>Sample Code:</b><br>
	 * <code>FilterArgument[] filterArguments = new FilterArgument[]{</code><br>
	 * <code>&emsp;&emsp;new FilterArgument("username", ECommonOperator.LIKE_BOTH_SIDE)</code><br>
	 * <code>}</code><br>
	 * <code>getObjectSQLNative("SELECT COUNT(*) FROM sec_user WHERE sec_user LIKE :username", filterArguments, new Object[]{"admin"})</code><br><br>
	 * @param sql
	 * @param filterArguments
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public Object getObjectSQLNative(String sql, FilterArgument[] filterArguments, Object[] values) throws Exception;
	
}