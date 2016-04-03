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
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.dto.app.MenuData;
import com.tripoin.dto.app.RoleData;
import com.tripoin.dto.app.UserData;
import com.tripoin.dto.request.DTORequestLogin;
import com.tripoin.dto.response.DTOResponseLogin;

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

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/login-menu</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER, RoleConstant.ROLE_GATEWAY, RoleConstant.ROLE_ADMIN})
	public Message<DTOResponseLogin> getUserMenu(Message<DTORequestLogin> inMessage){
		DTOResponseLogin dtoResponseLogin = new DTOResponseLogin();
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
				viewType = inMessage.getPayload().getViewType();
			}			
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("username", ECommonOperator.EQUALS) 
			};
			List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[] { this.currentUserName }, null, null);
			if (userList != null) {
				User user = userList.get(0);
				UserData userData = new UserData();
				userData.setUsername(user.getUsername());
				userData.setAuth(user.getAuth());
				userData.setEnabled(user.getEnabled());
				if(user.getExpiredDate() != null)
					userData.setExpiredDate(ParameterConstant.FORMAT_DEFAULT.format(user.getExpiredDate()));
				userData.setNonLocked(user.getNonLocked());
				userData.setStatus(user.getStatus());
				userData.setRemarks(user.getRemarks());
				RoleData roleData = new RoleData();
				roleData.setCode(user.getRole().getCode());
				roleData.setStatus(user.getRole().getStatus());
				roleData.setRemarks(user.getRole().getRemarks());
				userData.setRoleData(roleData);
				dtoResponseLogin.setUserData(userData);
				userData = null;
				roleData = null;
			}
			List<Menu> menuList = iGenericManagerJpa.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? "
					+ "AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", 
					new Object[] { this.currentRole, this.viewType, ParameterConstant.VIEW_WEB_MOBILE }, null);
			if (menuList != null) {
				List<MenuData> menuDatas = new ArrayList<MenuData>();
				for (Menu menu : menuList) {
					menuDatas.add(getMenuData(menu));
				}
				dtoResponseLogin.setMenuDatas(menuDatas);
				menuDatas = null;
			}
			dtoResponseLogin.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			dtoResponseLogin.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			dtoResponseLogin.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
			userList = null;
			filterArguments = null;
			menuList = null;
		} catch (WSEndpointFaultException e) {	
			dtoResponseLogin.setResponseCode(e.getMessage());
			dtoResponseLogin.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			dtoResponseLogin.setResponseDesc(e.getFaultInfo().getMessage());
        }  catch (Exception e) {
			LOGGER.error("Login Menu System Error : "+e.getMessage(), e);
			dtoResponseLogin.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			dtoResponseLogin.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			dtoResponseLogin.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
		}
		setReturnStatusAndMessage(dtoResponseLogin, responseHeaderMap);
		Message<DTOResponseLogin> message = new GenericMessage<DTOResponseLogin>(dtoResponseLogin, responseHeaderMap);
		dtoResponseLogin = null;
		this.currentUserName = null;
		this.currentRole = null;
		return message;	
	}

	public MenuData getMenuData(Menu menu){
		MenuData menuData = new MenuData();
		menuData.setCode(menu.getCode());
		menuData.setLevel(menu.getLevel());
		menuData.setOrder(menu.getOrder());
		menuData.setTree(menu.getTree());
		menuData.setViewType(menu.getViewType());
		menuData.setName(menu.getName());
		if(menu.getMenuParent() != null)
			menuData.setDtoMenu(getMenuData(menu.getMenuParent()));
		return menuData;
	}
	
}
