package com.tripoin.core.dao.util;

import java.util.List;

import com.tripoin.core.pojo.VersionControlSystemTable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IVersionControlSystemTableDao {
	
	public VersionControlSystemTable loadValue(String code) throws Exception;
	
	public List<VersionControlSystemTable> listValue(Object[] code) throws Exception;
	
	public int updateValue(Long value, String code) throws Exception;
	
	public int insertValue(String code, Long value, Long status, String remarks) throws Exception;
	
	public int insertValueAndSync(String code, Long status, String remarks) throws Exception;

}
