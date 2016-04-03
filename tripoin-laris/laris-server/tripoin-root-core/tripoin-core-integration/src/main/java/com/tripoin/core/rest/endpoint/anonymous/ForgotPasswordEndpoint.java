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
@Component("forgotPasswordEndpoint")
public class ForgotPasswordEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private ISystemParameterService systemParameterService;

	@Autowired
	private ICoreMailSender iCoreMailSender;

	private WSEndpointFault wsEndpointFault = new WSEndpointFault();
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

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/anonymous/forgotpassword</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<GeneralTransferObject> forgotPassword(Message<?> inMessage){
		GeneralTransferObject connect = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			if(inMessage.getPayload() != null){
				if(((String)inMessage.getPayload()).startsWith(ParameterConstant.FORGOT_PASSWORD_EMAIL))
					emailTo = ((String)inMessage.getPayload()).replaceAll(ParameterConstant.FORGOT_PASSWORD_EMAIL, "");
				else{
    				wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);					
				}
			}
			FilterArgument[] filterArguments = new FilterArgument[] { 
					new FilterArgument("email", ECommonOperator.EQUALS) 
			};
			List<Profile> profileList = iGenericManagerJpa.loadObjectsFilterArgument(Profile.class, filterArguments, new Object[] { emailTo }, null, null);
			if(profileList != null && !profileList.isEmpty()){
				filterArguments = new FilterArgument[] { 
						new FilterArgument("id", ECommonOperator.EQUALS) 
				};
				List<User> userList = iGenericManagerJpa.loadObjectsFilterArgument(User.class, filterArguments, new Object[] { profileList.get(0).getUser().getId() }, null, null);
				if(userList.get(0).getEnabled() != 1){	
					wsEndpointFault.setMessage(EResponseCode.RC_ACCOUNT_NOTACTIVE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_ACCOUNT_NOTACTIVE.getResponseCode(), wsEndpointFault);					
				}else if(userList.get(0).getExpiredDate() != null && new Date().after(userList.get(0).getExpiredDate())){
					wsEndpointFault.setMessage(EResponseCode.RC_ACCOUNT_EXPIRED.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_ACCOUNT_EXPIRED.getResponseCode(), wsEndpointFault);					
				}else{
				    UUID uuid = UUID.randomUUID();
				    Date expired = addDays(new Date(), 1);
				    ValueArgument[] valueArguments = new ValueArgument[]{
				    		new ValueArgument("uuid", uuid.toString()),
				    		new ValueArgument("expired", expired),
				    		new ValueArgument("email", emailTo)
				    };
				    iGenericManagerJpa.execQueryNotCriteria("UPDATE mst_profile SET profile_forgot_uuid = :uuid , "
				    		+ "profile_forgot_expired = :expired "
				    		+ "WHERE profile_email = :email", valueArguments);				    	
			    
				    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.FORGOT_PASSWORD_SUBJECT, ParameterConstant.FORGOT_PASSWORD_BODY});
				    Map<String, String> mapSystemParamter = new HashMap<String, String>();
				    for(SystemParameter systemParameter : systemParameters)
				    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
				    String content = mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_BODY);
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, profileList.get(0).getName());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, userList.get(0).getUsername());
				    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_URL, webAppHost.concat("/forgotpassword").concat("?user=").concat(userList.get(0).getUsername()).concat("&uuid=").concat(uuid.toString()));
				    try {
				    	iCoreMailSender.sendMailContent(emailFrom, emailTo, mapSystemParamter.get(ParameterConstant.FORGOT_PASSWORD_SUBJECT), content);
				    	LOGGER.debug("Email Forgot Password has been sent to : "+emailTo);	
					} catch (Exception e) {
						LOGGER.error("Forgot Password System Error : "+e.getMessage(), e);						
						wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
	    				throw new WSEndpointFaultException(EResponseCode.RC_FAILURE.getResponseCode(), wsEndpointFault);						
					}					
					connect.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
					connect.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
					connect.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
					mapSystemParamter = null;
					systemParameters = null;
					content = null;
					valueArguments = null;
					expired = null;
					uuid = null;
				}
				userList = null;
			}else{
				connect.setResponseCode(EResponseCode.RC_EMAIL_NOTREGISTER.getResponseCode());
				connect.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				connect.setResponseDesc(EResponseCode.RC_EMAIL_NOTREGISTER.toString());				
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
		emailTo = null;
		return message;	
	}
	
	private static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
	
}
