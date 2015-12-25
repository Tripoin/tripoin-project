package com.tripoin.core.dao.util.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tripoin.core.dao.util.ISystemParameterDao;
import com.tripoin.core.mapper.SystemParameterMapper;
import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class SystemParameterDaoImpl implements ISystemParameterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<SystemParameter> systemParameterMapper = new SystemParameterMapper();
	
	@Override
	public SystemParameter loadValue(String code) throws Exception {
		SystemParameter systemParameter = (SystemParameter)jdbcTemplate.queryForObject("SELECT * FROM mst_system_parameter WHERE system_parameter_code = ?", new Object[]{code}, systemParameterMapper);
		return systemParameter;
	}

	@Override
	public List<SystemParameter> listValue(Object[] code) throws Exception {
		List<SystemParameter> systemParameterList = jdbcTemplate.query("SELECT * FROM mst_system_parameter WHERE system_parameter_code = ? OR system_parameter_code = ?", code, systemParameterMapper);
		return systemParameterList;
	}

}
