package com.tripoin.core.rest.endpoint.anonymous;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.util.mail.ICoreMailSender;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("forgotPasswordEndpoint")
public class ForgotPasswordEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private ISystemParameterService systemParameterService;

	@Autowired
	private ICoreMailSender iCoreMailSender;
	
	private String emailTo;
	private String emailFrom;
	private String webAppHost;

	@Value("${tripoin.email.from}")
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	@Value("${tripoin.web.app.host}")
	public void setWebAppHost(String webAppHost) {
		this.webAppHost = webAppHost;
	}

	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<GeneralTransferObject> forgotPassword(Message<?> inMessage){
		GeneralTransferObject connect = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			if(inMessage.getPayload() != null)
				emailTo = ((String)inMessage.getPayload()).replaceAll(ParameterConstant.FORGOT_PASSWORD_EMAIL, "");
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("email", ECommonOperator.EQUALS) 
			};
			List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { emailTo }, null, null);
			if(profileList != null && profileList.size() > 0 && profileList.get(0) != null){
				if(profileList.get(0).getUser().getEnabled() != 1){					
					connect.setResponseCode("2");
					connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
					connect.setResponseDesc("Account is no longer active");					
				}else if(profileList.get(0).getUser().getExpiredDate() != null && new Date().after(profileList.get(0).getUser().getExpiredDate())){					
					connect.setResponseCode("3");
					connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
					connect.setResponseDesc("Account is expired");					
				}else{
				    Profile profile = profileList.get(0);
				    if(profile.getForgotExpired() == null || new Date().after(profile.getForgotExpired())){
					    UUID uuid = UUID.randomUUID();
					    Date expired = addDays(new Date(), 1);
					    profile.setForgotUUID(uuid.toString());
					    profile.setForgotExpired(expired);
					    iGenericManagerJpa.updateObject(profile);				    	
				    }
				    
				    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_SUBJECT, ParameterConstant.FORGOT_PASSWORD_BODY});
				    Map<String, String> mapSystemParamter = new HashMap<String, String>();
				    for(SystemParameter systemParameter : systemParameters)
				    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
				    String content = mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_BODY);
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profile.getName());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, profile.getUser().getUsername());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_URL, webAppHost.concat("/forgotpassword").concat("?user=").concat(profile.getUser().getUsername()).concat("&uuid=").concat(profile.getForgotUUID()));
					iCoreMailSender.sendMailContent(emailFrom, emailTo, mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_SUBJECT), content);
					connect.setResponseCode("0");
					connect.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
					connect.setResponseDesc("Forgot Password Success");					
				}	
			}else{
				connect.setResponseCode("4");
				connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				connect.setResponseDesc("Email is not registered");				
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
	
	private static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
	
}
