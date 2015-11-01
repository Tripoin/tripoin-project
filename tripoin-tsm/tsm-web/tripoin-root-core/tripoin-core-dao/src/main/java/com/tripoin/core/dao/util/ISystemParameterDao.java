package com.tripoin.core.dao.util;

import java.util.List;

import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISystemParameterDao {
	
	public SystemParameter loadValue(String code);
	
	public List<SystemParameter> listValue(Object[] code);

}
