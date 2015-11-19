package com.tripoin.core.soap.implementor;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserMenuTransferObject;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.endpoint.ILoginMenuServiceEndpoint;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@WebService(endpointInterface = "com.tripoin.core.service.soap.endpoint.ILoginMenuServiceEndpoint", serviceName = "loginMenuService")
public class LoginMenuImpl implements ILoginMenuServiceEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginMenuImpl.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	private String currentRole;
	
	private String viewType = ParameterConstant.VIEW_WEB_MOBILE;

	@Override
	public UserMenuTransferObject getUserMenu(String viewType) throws WSEndpointFaultException {
		UserMenuTransferObject userMenuTransferObject = new UserMenuTransferObject();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    this.currentUserName = authentication.getName();
		    GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
		    this.currentRole = grantedAuthority.getAuthority();
		}		
			
		try {
			if(viewType != null)
				this.viewType = viewType;			
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("username", ECommonOperator.EQUALS) 
			};
			List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[] { this.currentUserName }, null, null);
			if (userList != null) {
				UserData userData = new UserData(userList.get(0));
				List<UserData> userDatas = new ArrayList<UserData>();
				userDatas.add(userData);
				userMenuTransferObject.setUserDatas(userDatas);
			}
			List<Menu> menuList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", new Object[] { this.currentRole, this.viewType, ParameterConstant.VIEW_WEB_MOBILE }, null);
			if (menuList != null) {
				List<MenuData> menuDatas = new ArrayList<MenuData>();
				for (Menu menu : menuList) {
					MenuData menuData = new MenuData(menu);
					menuDatas.add(menuData);
				}
				userMenuTransferObject.setMenuDatas(menuDatas);
			}
			userMenuTransferObject.setResponseCode("0");
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			userMenuTransferObject.setResponseDesc("Login Menu Success");
		} catch (Exception e) {
			LOGGER.error("Login Menu System Error : "+e.getMessage(), e);
			userMenuTransferObject.setResponseCode("1");
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			userMenuTransferObject.setResponseDesc("Login Menu System Error : " + e.getMessage());
		}
		return userMenuTransferObject;
	}

}
