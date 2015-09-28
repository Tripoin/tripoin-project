package com.tripoin.web.authentication.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tripoin.core.common.RoleConstant;
import com.tripoin.web.authentication.IAccessControl;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class AccessControlImpl implements IAccessControl {

    private static Logger LOGGER = LoggerFactory.getLogger(AccessControlImpl.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;

	@Override
    public boolean signIn(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.isAuthenticated()){
            	LOGGER.debug("User [{}] successfully logged in application", username);
            	return true;            	
            }
        } catch (BadCredentialsException bce) {
        	SecurityContextHolder.clearContext();
        	LOGGER.warn("Cannot login : {}", bce.getMessage());
        } catch (Exception e) {
        	SecurityContextHolder.clearContext();
        	LOGGER.warn("Authentication failed for user [{}]", username);
        }
        return false;
    }

    @Override
    public boolean isUserSignedIn() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().size() > 0){
        	for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
        		if(!RoleConstant.ROLE_ANONYMOUS.equals(grantedAuthority.getAuthority()))
        			return true;
        	}
        }
        return false;
    }

    @Secured({RoleConstant.ROLE_SUPERADMIN, RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	@Override
	public String getUsername() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication == null)
    		return null;
		return authentication.getName();
	}

	@Secured({RoleConstant.ROLE_SUPERADMIN, RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER})
	@Override
	public String getRole() {	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if(authentication == null)
    		return null;
    	GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
		return grantedAuthority.getAuthority();
	}   

}
