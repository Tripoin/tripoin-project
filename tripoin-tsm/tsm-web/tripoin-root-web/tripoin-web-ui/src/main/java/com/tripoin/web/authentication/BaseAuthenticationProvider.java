package com.tripoin.web.authentication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserMenuTransferObject;
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
    		UserMenuTransferObject userMenuTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_LOGIN_MENU), UserMenuTransferObject.class);
            if(userMenuTransferObject != null){
            	List<MenuData> menuDatas = userMenuTransferObject.getMenuDatas();
            	UserData userData = userMenuTransferObject.getUserDatas().get(0);
            	if(menuDatas != null){
            		Map<String, String> additionalDataMenu = new LinkedHashMap<String, String>();
            		for(MenuData menuData : menuDatas)
            			additionalDataMenu.put(menuData.getCode(), menuData.getName());
            		stateFullRest.setAdditionalDataMenu(additionalDataMenu);
            	}else{
            		return null;
            	}
            	if(userData != null){
            		if(userData.getRoleData() != null){
                        List<GrantedAuthority> grantedAuths = new ArrayList<>();
                        grantedAuths.add(new SimpleGrantedAuthority(userData.getRoleData().getCode()));
                        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
                        LOGGER.debug("User [{}] successfully logged in web service", username);
                        return auth;            			
            		}else{
            			return null;
            		}
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