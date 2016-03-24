package com.tripoin.core.service.util;

import java.util.List;

import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISystemParameterService {	
	
	public SystemParameter getParameter(String name) throws Exception;
	
	public List<SystemParameter> listValue(Object[] code) throws Exception;
	
}
