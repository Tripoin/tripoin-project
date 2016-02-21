package com.tripoin.core.rest.endpoint.user;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserTransferObject;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("userLoadEndpoint")
public class UserLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(UserLoadEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;
    
	private String currentUserName;

    @Secured({RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<UserTransferObject> loadAllUser(Message<?> inMessage) {
        UserTransferObject userTransferObject = new UserTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        try {
            List<User> userList = iGenericManagerJpa.loadObjects(User.class);
            List<UserData> userDatas = new ArrayList<UserData>();
            if (userList != null) {
                for (User user : userList) {
                    UserData userData = new UserData(user);
                    userDatas.add(userData);
                }
                userTransferObject.setUserDatas(userDatas);
            }
            userTransferObject.setResponseCode("0");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            userTransferObject.setResponseDesc("Load All User Data Success");
        } catch (Exception e) {
            LOGGER.error("Load All User System Error : " + e.getLocalizedMessage(), e);
            userTransferObject.setResponseCode("1");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            userTransferObject.setResponseDesc("Load All User System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(userTransferObject, responseHeaderMap);
        Message<UserTransferObject> message = new GenericMessage<UserTransferObject>(userTransferObject, responseHeaderMap);
        return message;
    }

    @Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<UserTransferObject> loadUser(Message<?> inMessage) {
        UserTransferObject userTransferObject = new UserTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
        try {
        	FilterArgument[] filterArguments = new FilterArgument[]{
					new FilterArgument("username", ECommonOperator.EQUALS)
			};
			List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[]{currentUserName}, null, null);
			if(userList != null){
				UserData userData = new UserData(userList.get(0));
				List<UserData> userDatas = new ArrayList<UserData>();
				userDatas.add(userData);
				userTransferObject.setUserDatas(userDatas);
			}
            userTransferObject.setResponseCode("0");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            userTransferObject.setResponseDesc("Load User Data Success");
        } catch (Exception e) {
            LOGGER.error("Load User System Error : " + e.getLocalizedMessage(), e);
            userTransferObject.setResponseCode("1");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            userTransferObject.setResponseDesc("Load User System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(userTransferObject, responseHeaderMap);
        Message<UserTransferObject> message = new GenericMessage<UserTransferObject>(userTransferObject, responseHeaderMap);
        return message;
    }

}
