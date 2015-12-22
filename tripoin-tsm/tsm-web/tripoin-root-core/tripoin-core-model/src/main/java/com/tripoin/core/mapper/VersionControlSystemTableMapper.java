package com.tripoin.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tripoin.core.pojo.VersionControlSystemTable;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class VersionControlSystemTableMapper implements RowMapper<VersionControlSystemTable> {
	public VersionControlSystemTable mapRow(ResultSet rs, int rowNum) throws SQLException {
		VersionControlSystemTable vcsTable = new VersionControlSystemTable();
		vcsTable.setId(rs.getLong("vcs_table_id"));
		vcsTable.setCode(rs.getString("vcs_table_code"));
		vcsTable.setTotalRow(rs.getLong("vcs_table_total_row"));
		vcsTable.setStatus(rs.getInt("vcs_table_status"));
		vcsTable.setRemarks(rs.getString("vcs_table_remarks"));
		vcsTable.setCreatedBy(rs.getString("vcs_table_created_by"));
		vcsTable.setCreatedIP(rs.getString("vcs_table_created_ip"));
		vcsTable.setCreatedTime(rs.getDate("vcs_table_created_time"));
		vcsTable.setCreatedPlatform(rs.getString("vcs_table_created_platform"));
		vcsTable.setModifiedBy(rs.getString("vcs_table_modified_by"));
		vcsTable.setModifiedIP(rs.getString("vcs_table_modified_ip"));
		vcsTable.setModifiedTime(rs.getDate("vcs_table_modified_time"));
		vcsTable.setModifiedPlatform(rs.getString("vcs_table_modified_platform"));
		return vcsTable;
	}
 
}