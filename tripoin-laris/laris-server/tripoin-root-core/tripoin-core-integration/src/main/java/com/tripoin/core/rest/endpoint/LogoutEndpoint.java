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

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/logout</code><br>
	 * @param inMessage
	 */
	@Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER, RoleConstant.ROLE_GATEWAY, RoleConstant.ROLE_ADMIN})
	public void doLogout(Message<?> inMessage){	
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
			    currentUserName = authentication.getName();
			}
			authentication = null;
			SecurityContextHolder.clearContext();			
		} catch (Exception e) {
			LOGGER.error("Error on logout : ".concat(e.getMessage()), e);
		}
		LOGGER.debug(currentUserName.concat(" has been logout."));		
	}	
	
}
