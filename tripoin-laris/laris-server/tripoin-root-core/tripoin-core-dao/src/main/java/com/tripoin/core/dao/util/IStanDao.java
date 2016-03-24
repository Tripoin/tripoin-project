package com.tripoin.core.dao.util;

import com.tripoin.core.pojo.Stan;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IStanDao {
	public void updateStan(Long stanCounter, Long stanId) throws Exception;
	
	public Stan loadStan(Long userId) throws Exception;
}
