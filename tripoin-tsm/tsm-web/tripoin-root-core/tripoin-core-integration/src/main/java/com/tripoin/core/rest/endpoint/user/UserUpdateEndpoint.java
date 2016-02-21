package com.tripoin.core.rest.endpoint.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserTransferObject;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("userUpdateEndpoint")
public class UserUpdateEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(UserUpdateEndpoint.class);

    @Autowired
    private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private StandardStringDigester jasyptStringDigester;

    @Secured({RoleConstant.ROLE_SALESMAN, RoleConstant.ROLE_AREASALESMANAGER, RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
    public Message<UserTransferObject> updateUser(Message<UserData> inMessage) {
        UserTransferObject userTransferObject = new UserTransferObject();
        Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

        try {
        	UserData userDataPayload = inMessage.getPayload();       	
            User userPayload = new User(userDataPayload);
        	if(userDataPayload.getAuth() != null){
	        	String basicAuthorization = userDataPayload.getAuth();
	        	byte[] oauth = Base64.decode(basicAuthorization.getBytes());
	        	userDataPayload.setAuth(null);
	            userPayload.setAuth(null);
	            userPayload.setPassword(jasyptStringDigester.digest(new String(oauth).split(":")[1]));	            
        	}        	
            iGenericManagerJpa.updateObject(userPayload);
            List<UserData> userDatas = new ArrayList<UserData>();
            userDatas.add(userDataPayload);
            userTransferObject.setUserDatas(userDatas);
            userTransferObject.setResponseCode("0");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
            userTransferObject.setResponseDesc("Update User Data Success");
        } catch (Exception e) {
            LOGGER.error("Update User System Error : " + e.getLocalizedMessage(), e);
            userTransferObject.setResponseCode("1");
            userTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
            userTransferObject.setResponseDesc("Update User System Error : " + e.getLocalizedMessage());
        }

        setReturnStatusAndMessage(userTransferObject, responseHeaderMap);
        Message<UserTransferObject> message = new GenericMessage<UserTransferObject>(userTransferObject, responseHeaderMap);
        return message;
    }

}
