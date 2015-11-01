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

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ISystemParameterService;
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
	
	private String emailTo;
	private String emailFrom;
	private String username;
	private String uuid;

	@Value("${tripoin.email.from}")
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<GeneralTransferObject> forgotPasswordVerify(Message<?> inMessage){
		GeneralTransferObject connect = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			if(inMessage.getPayload() != null){
				String[] rawParameter = ((String)inMessage.getPayload()).split("&");
				username = rawParameter[0].replaceAll(ParameterConstant.FORGOT_PASSWORD_USERNAME, "");
				uuid = rawParameter[1].replaceAll(ParameterConstant.FORGOT_PASSWORD_UUID, "");	
				System.out.println(username);	
				System.out.println(uuid);
			}
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("user.username", ECommonOperator.EQUALS),
					new FilterArgument("forgotUUID", ECommonOperator.EQUALS) 
			};
			List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { username, uuid }, null, null);
			if(profileList != null && profileList.size() > 0 && profileList.get(0) != null){
				if(profileList.get(0).getUser().getExpiredDate() != null && new Date().after(profileList.get(0).getUser().getExpiredDate())){					
					connect.setResponseCode("2");
					connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
					connect.setResponseDesc("Link has been expired");					
				}else{
				    Profile profile = profileList.get(0);
				    emailTo = profile.getEmail();
				    User user = profileList.get(0).getUser();
				    String passwordPlainText = randomGeneratorAlphanumeric(7);
				    String password = jasyptStringDigester.digest(passwordPlainText);
				    user.setPassword(password);
					iGenericManagerJpa.updateObject(user);
				    
				    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_VERIFY_SUBJECT, ParameterConstant.FORGOT_PASSWORD_VERIFY_BODY});
				    Map<String, String> mapSystemParamter = new HashMap<String, String>();
				    for(SystemParameter systemParameter : systemParameters)
				    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
				    String content = mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_VERIFY_BODY);
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profile.getName());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, profile.getUser().getUsername());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_PASSWORD, passwordPlainText);
					iCoreMailSender.sendMailContent(emailFrom, emailTo, mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_VERIFY_SUBJECT), content);
					connect.setResponseCode("0");
					connect.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
					connect.setResponseDesc("Verify Forgot Password Success");					
				}	
			}else{
				connect.setResponseCode("3");
				connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				connect.setResponseDesc("Link is not found");				
			}
		}catch (Exception e){
			LOGGER.error("Forgot Password System Error : "+e.getMessage(), e);
			connect.setResponseCode("1");
			connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			connect.setResponseDesc("Forgot Password System Error : "+e.getMessage());
		}
		
		setReturnStatusAndMessage(connect, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(connect, responseHeaderMap);
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
