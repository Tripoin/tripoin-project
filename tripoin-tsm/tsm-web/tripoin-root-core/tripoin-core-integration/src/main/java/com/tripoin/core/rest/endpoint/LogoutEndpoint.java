package com.tripoin.core.rest.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.RoleConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("logoutEndpoint")
public class LogoutEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(LogoutEndpoint.class);

	private String currentUserName;

	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public void doLogout(Message<?> inMessage){	
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			    currentUserName = authentication.getName();
			}		
			SecurityContextHolder.clearContext();			
		} catch (Exception e) {
			LOGGER.error("Error on logout : ".concat(e.getMessage()), e);
		}
		LOGGER.debug(currentUserName.concat(" has been logout."));		
	}	
	
}
