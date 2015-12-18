package com.tripoin.core.dao.util.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tripoin.core.dao.util.IVersionControlSystemTableDao;
import com.tripoin.core.mapper.VersionControlSystemTableMapper;
import com.tripoin.core.pojo.VersionControlSystemTable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Repository
public class VersionControlSystemTableDaoImpl implements IVersionControlSystemTableDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final RowMapper<VersionControlSystemTable> versionControlSystemTableMapper = new VersionControlSystemTableMapper();
	
	@Override
	public VersionControlSystemTable loadValue(String code) {
		VersionControlSystemTable versionControlSystemTable = (VersionControlSystemTable)jdbcTemplate.queryForObject("SELECT * FROM vcs_table WHERE vcs_table_code = ?", new Object[]{code}, versionControlSystemTableMapper);
		return versionControlSystemTable;
	}

	@Override
	public List<VersionControlSystemTable> listValue(Object[] code) {
		List<VersionControlSystemTable> versionControlSystemTableList = jdbcTemplate.query("SELECT * FROM vcs_table WHERE vcs_table_code = ? OR vcs_table_code = ?", code, versionControlSystemTableMapper);
		return versionControlSystemTableList;
	}

	@Override
	public int updateValue(Long value, String code) {
		return jdbcTemplate.update("UPDATE FROM vcs_table SET vcs_table_value = ? WHERE vcs_table_code = ?", new Object[]{value, code});
	}

	@Override
	public int insertValue(String code, Long value, Long status, String remarks) {
		return jdbcTemplate.update("INSERT INTO vcs_table(vcs_table_code,  vcs_table_value,  vcs_table_status,  vcs_table_remarks) VALUES (?, ?, ?, ?)", new Object[]{code, value, status, remarks});
	}

	@Override
	public int insertValueAndSync(String code, Long status, String remarks) {		
		Map<String, Object> dataMap = jdbcTemplate.queryForMap("SELECT COUNT(*) TOTAL_ROW FROM ".concat(code));
		int result = updateValue(new Long(dataMap.get("TOTAL_ROW").toString()), code);
		if(result == 0) return insertValue(code, new Long(dataMap.get("TOTAL_ROW").toString()), status, remarks);
		return result;
	}

}
