package com.tripoin.core.rest.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

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
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserMenuTransferObject;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.core.servlet.ApplicationContextConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("loginMenuEndpoint")
public class LoginMenuEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginMenuEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
    private ServletContext context;

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
	@SuppressWarnings("unchecked")
	@Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
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
			List<User> userList = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[] { this.currentUserName }, null);
			if (userList != null) {
				List<UserData> userDatas = new ArrayList<UserData>();
				userDatas.add(new UserData(userList.get(0)));
				userMenuTransferObject.setUserDatas(userDatas);
				userDatas = null;
			}else
				throw new Exception();

			List<MenuData> menuDatas = new ArrayList<MenuData>();
			switch (this.currentRole) {
				case RoleConstant.ROLE_ADMIN:
					if(ParameterConstant.VIEW_WEB.equals(this.viewType))
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_ADMIN_WEB);
					else
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_ADMIN_MOBILE);
					break;
				case RoleConstant.ROLE_SALESMAN:
					if(ParameterConstant.VIEW_WEB.equals(this.viewType))
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_SALESMAN_WEB);
					else
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_SALESMAN_MOBILE);
					break;
				case RoleConstant.ROLE_AREASALESMANAGER:
					if(ParameterConstant.VIEW_WEB.equals(this.viewType))
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_AREASALESMANAGER_WEB);
					else
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_AREASALESMANAGER_MOBILE);
					break;
				case RoleConstant.ROLE_NATIONALSALESMANAGER:
					if(ParameterConstant.VIEW_WEB.equals(this.viewType))
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_NATIONALSALESMANAGER_WEB);
					else
						menuDatas = (List<MenuData>)context.getAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_NATIONALSALESMANAGER_MOBILE);
					break;
				default:
					throw new Exception();
			}
			if(menuDatas == null || menuDatas.isEmpty()){
				List<Menu> menuList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", new Object[] { this.currentRole, this.viewType, ParameterConstant.VIEW_WEB_MOBILE }, null);
				if (menuList != null) {
					for (Menu menu : menuList) {
						MenuData menuData = new MenuData(menu);
						menuDatas.add(menuData);
					}
				}
				menuList = null;
			}
			userMenuTransferObject.setMenuDatas(menuDatas);
			userMenuTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			userMenuTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			userMenuTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
			userList = null;
			menuDatas = null;
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
