package com.tripoin.core.rest.endpoint.anonymous;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.util.mail.ICoreMailSender;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("forgotPasswordVerifyEndpoint")
public class ForgotPasswordVerifyEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordVerifyEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private ISystemParameterService systemParameterService;

	@Autowired
	private StandardStringDigester jasyptStringDigester;
	
	@Autowired
	private ICoreMailSender iCoreMailSender;

	private WSEndpointFault wsEndpointFault = new WSEndpointFault();
	private String username;
	
	private String emailFrom;
	private String uuid;

	@Value("${tripoin.email.from}")
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/anonymous/forgotpassword/verify</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<GeneralTransferObject> forgotPasswordVerify(Message<?> inMessage){
		GeneralTransferObject connect = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			if(inMessage.getPayload() != null){
				String[] rawParameter = ((String)inMessage.getPayload()).split("&");
				if(rawParameter != null && rawParameter.length == 2){
					if(rawParameter[0].startsWith(ParameterConstant.FORGOT_PASSWORD_USERNAME))
						username = rawParameter[0].replaceAll(ParameterConstant.FORGOT_PASSWORD_USERNAME, "");
					else{
	    				wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
	    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);					
					}
					if(rawParameter[1].startsWith(ParameterConstant.FORGOT_PASSWORD_UUID))
						uuid = rawParameter[1].replaceAll(ParameterConstant.FORGOT_PASSWORD_UUID, "");
					else{
	    				wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
	    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);					
					}					
				}
				rawParameter = null;
			}
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("user.username", ECommonOperator.EQUALS),
					new FilterArgument("forgotUUID", ECommonOperator.EQUALS) 
			};
			List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { username, uuid }, null, null);
			if(profileList != null && !profileList.isEmpty()){
				filterArguments = new FilterArgument[] { 
						new FilterArgument("username", ECommonOperator.EQUALS) 
				};
				List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[] { username }, null, null);
				if(userList.get(0).getExpiredDate() != null && new Date().after(userList.get(0).getExpiredDate())){	
					wsEndpointFault.setMessage(EResponseCode.RC_URL_EXPIRED.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_URL_EXPIRED.getResponseCode(), wsEndpointFault);					
				}else{
				    String passwordPlainText = randomGeneratorAlphanumeric(7);
				    String password = jasyptStringDigester.digest(passwordPlainText);
				    ValueArgument[] valueArguments = new ValueArgument[]{
				    		new ValueArgument("password", password),
				    		new ValueArgument("username", userList.get(0).getUsername())
				    };
				    iGenericManagerJpa.execQueryNotCriteria("UPDATE sec_user SET user_password = :password "
				    		+ "WHERE user_username = :username", valueArguments);	
				    
				    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_VERIFY_SUBJECT, ParameterConstant.FORGOT_PASSWORD_VERIFY_BODY});
				    Map<String, String> mapSystemParamter = new HashMap<String, String>();
				    for(SystemParameter systemParameter : systemParameters)
				    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
				    String content = mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_VERIFY_BODY);
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profileList.get(0).getName());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, userList.get(0).getUsername());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_PASSWORD, passwordPlainText);
					iCoreMailSender.sendMailContent(emailFrom, profileList.get(0).getEmail(), mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_VERIFY_SUBJECT), content);
					LOGGER.debug("Email Verify Forgot Password has been sent to : "+profileList.get(0).getEmail());
					connect.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
					connect.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
					connect.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
					valueArguments = null;
					passwordPlainText = null;
					password = null;
					content = null;
					mapSystemParamter = null;
					systemParameters = null;
					userList = null;
				}	
			}else{	
				wsEndpointFault.setMessage(EResponseCode.RC_URL_NOTFOUND.toString());
				throw new WSEndpointFaultException(EResponseCode.RC_URL_NOTFOUND.getResponseCode(), wsEndpointFault);				
			}
			filterArguments = null;
			profileList = null;
		} catch (WSEndpointFaultException e) {	
			connect.setResponseCode(e.getMessage());
			connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			connect.setResponseDesc(e.getFaultInfo().getMessage());
        } catch (Exception e){
			LOGGER.error("Forgot Password System Error : "+e.getMessage(), e);
			connect.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			connect.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getMessage());
		}
		
		setReturnStatusAndMessage(connect, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(connect, responseHeaderMap);
		connect = null;
		username = null;
		uuid = null;
		return message;	
	}
	
	private static String randomGeneratorAlphanumeric(int length){
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuffer buffer = new StringBuffer();
		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}
	
}
