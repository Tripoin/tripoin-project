package com.tripoin.core.dao.util;

import java.util.List;

import com.tripoin.core.pojo.VersionControlSystemTable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IVersionControlSystemTableDao {
	
	public VersionControlSystemTable loadValue(String code);
	
	public List<VersionControlSystemTable> listValue(Object[] code);
	
	public int updateValue(Long value, String code);
	
	public int insertValue(String code, Long value, Long status, String remarks);
	
	public int insertValueAndSync(String code, Long status, String remarks);

}
