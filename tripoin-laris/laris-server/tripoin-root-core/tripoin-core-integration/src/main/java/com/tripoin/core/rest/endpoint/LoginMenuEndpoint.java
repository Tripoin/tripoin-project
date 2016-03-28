package com.tripoin.core.rest.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserMenuTransferObject;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("loginMenuEndpoint")
public class LoginMenuEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginMenuEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	private String currentRole;
	
	private String viewType = ParameterConstant.VIEW_WEB_MOBILE;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/login-menu</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER, RoleConstant.ROLE_GATEWAY, RoleConstant.ROLE_ADMIN})
	public Message<UserMenuTransferObject> getUserMenu(Message<?> inMessage){
		UserMenuTransferObject userMenuTransferObject = new UserMenuTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    this.currentUserName = authentication.getName();
		    GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
		    this.currentRole = grantedAuthority.getAuthority();
		}		
		authentication = null;
		try {
			if(inMessage.getPayload() != null){
				if(((String)inMessage.getPayload()).startsWith(ParameterConstant.VIEW_TYPE))
					viewType = ((String)inMessage.getPayload()).replaceAll(ParameterConstant.VIEW_TYPE, "");
				else{
    				wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);					
				}
			}			
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("username", ECommonOperator.EQUALS) 
			};
			List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[] { this.currentUserName }, null, null);
			if (userList != null) {
				UserData userData = new UserData(userList.get(0));
				List<UserData> userDatas = new ArrayList<UserData>();
				userDatas.add(userData);
				userMenuTransferObject.setUserDatas(userDatas);
				userData = null;
				userDatas = null;
			}
			List<Menu> menuList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", new Object[] { this.currentRole, this.viewType, ParameterConstant.VIEW_WEB_MOBILE }, null);
			if (menuList != null) {
				List<MenuData> menuDatas = new ArrayList<MenuData>();
				for (Menu menu : menuList) {
					MenuData menuData = new MenuData(menu);
					menuDatas.add(menuData);
				}
				userMenuTransferObject.setMenuDatas(menuDatas);
				menuDatas = null;
			}
			userMenuTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			userMenuTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
			userList = null;
			filterArguments = null;
			menuList = null;
		} catch (WSEndpointFaultException e) {	
			userMenuTransferObject.setResponseCode(e.getMessage());
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			userMenuTransferObject.setResponseDesc(e.getFaultInfo().getMessage());
        }  catch (Exception e) {
			LOGGER.error("Login Menu System Error : "+e.getMessage(), e);
			userMenuTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			userMenuTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
		}
		setReturnStatusAndMessage(userMenuTransferObject, responseHeaderMap);
		Message<UserMenuTransferObject> message = new GenericMessage<UserMenuTransferObject>(userMenuTransferObject, responseHeaderMap);
		userMenuTransferObject = null;
		this.currentUserName = null;
		this.currentRole = null;
		return message;	
	}
	
}
