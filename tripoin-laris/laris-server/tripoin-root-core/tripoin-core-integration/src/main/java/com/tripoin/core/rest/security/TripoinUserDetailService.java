package com.tripoin.core.rest.security;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tripoin.core.pojo.User;
import com.tripoin.core.security.SecurityUser;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class TripoinUserDetailService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(TripoinUserDetailService.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Override
	public UserDetails loadUserByUsername(String argument) throws UsernameNotFoundException {
		SecurityUser securityUser = null;
		try {
			List<User> users = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr.user FROM Profile pr WHERE pr.user.username = ? OR pr.phone = ? OR pr.email = ?", new Object[]{argument, argument, argument}, null);
			if(users.size() == 0)
				users = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{argument}, null);
			if(users.size() > 0)
				securityUser = new SecurityUser(users.get(0));
		} catch (SQLException se){
			LOGGER.error("Tripoin Error Connection Database",se);
		} catch (Exception e) {
			LOGGER.error("Tripoin Error",e);
		}
		return securityUser;
	}

}
