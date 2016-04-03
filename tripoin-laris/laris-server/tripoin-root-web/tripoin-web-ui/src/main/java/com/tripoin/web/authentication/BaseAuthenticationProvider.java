package com.tripoin.web.authentication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.dto.app.MenuData;
import com.tripoin.dto.app.UserData;
import com.tripoin.dto.request.DTORequestLogin;
import com.tripoin.dto.response.DTOResponseLogin;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class BaseAuthenticationProvider implements AuthenticationProvider {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseAuthenticationProvider.class);

	private ICommonRest commonRest;
	private IStateFullRest stateFullRest;
	
	public void setCommonRest(ICommonRest commonRest) {
		this.commonRest = commonRest;
	}

	public void setStateFullRest(IStateFullRest stateFullRest) {
		this.stateFullRest = stateFullRest;
	}
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        try{
        	stateFullRest.setUsername(username);
        	stateFullRest.setPassword(password);
        	DTORequestLogin dtoRequestLogin = new DTORequestLogin();
    		dtoRequestLogin.setViewType(ParameterConstant.VIEW_WEB);
    		DTOResponseLogin dtoResponseLogin = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_LOGIN_MENU), dtoRequestLogin, DTOResponseLogin.class);
            if(dtoResponseLogin != null){
            	List<MenuData> menuDatas = dtoResponseLogin.getMenuDatas();
            	UserData userData = dtoResponseLogin.getUserData();
            	if(menuDatas != null)
            		stateFullRest.setMenuDatas(menuDatas);
            	else
            		return null;            	
            	if(userData != null){
                    List<GrantedAuthority> grantedAuths = new ArrayList<>();
                    grantedAuths.add(new SimpleGrantedAuthority(userData.getRoleData().getCode()));
                    Authentication auth = new UsernamePasswordAuthenticationToken(userData.getUsername(), password, grantedAuths);
                    LOGGER.debug("User [{}] successfully logged in web service", userData.getUsername());
                    return auth; 
            	}else{
            		return null;
            	}
            }else{
                return null;
            }	
        }catch(Exception e){
        	LOGGER.error(e.getMessage(), e);
        	return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}