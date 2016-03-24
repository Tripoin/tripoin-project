package com.tripoin.core.service.util.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dao.util.IVersionControlSystemTableDao;
import com.tripoin.core.pojo.VersionControlSystemTable;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("versionControlSystemTableService")
public class VersionControlSystemTableServiceImpl implements IVersionControlSystemTableService {
	
	@Autowired
	private IVersionControlSystemTableDao iVersionControlSystemTableDao;

	@Override
	public VersionControlSystemTable loadValue(String code) throws Exception {
		return iVersionControlSystemTableDao.loadValue(code);
	}

	@Override
	public List<VersionControlSystemTable> listValue(Object[] code) throws Exception {
		return iVersionControlSystemTableDao.listValue(code);
	}

	@Override
	public int updateValue(Long value, String code) throws Exception {
		return iVersionControlSystemTableDao.updateValue(value, code);
	}

	@Override
	public int insertValue(String code, Long value, Long status, String remarks) throws Exception {
		return iVersionControlSystemTableDao.insertValue(code, value, status, remarks);
	}

	@Override
	public int insertValueAndSync(String code, Long status, String remarks) throws Exception {
		return iVersionControlSystemTableDao.insertValueAndSync(code, status, remarks);
	}

}
