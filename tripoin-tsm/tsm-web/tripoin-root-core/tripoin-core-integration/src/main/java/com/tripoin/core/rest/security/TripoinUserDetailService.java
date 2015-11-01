package com.tripoin.core.rest.security;

import java.util.List;

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

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Override
	public UserDetails loadUserByUsername(String argument) throws UsernameNotFoundException {
		SecurityUser securityUser = null;
		try {
			List<User> users = iGenericManagerJpa.loadObjectsJQLStatement("SELECT pr.user FROM Profile pr WHERE pr.user.username = ?", new Object[]{argument}, null);
			securityUser = new SecurityUser(users.get(0));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return securityUser;
	}

}
