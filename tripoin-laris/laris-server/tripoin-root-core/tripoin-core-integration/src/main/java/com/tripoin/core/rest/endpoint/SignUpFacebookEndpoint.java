package com.tripoin.core.rest.endpoint;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.ValueArgument;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.dto.request.bca.DTORequestUserRegistrationBCA;
import com.tripoin.core.dto.response.bca.DTOResponseUserRegistrationBCA;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.pojo.Currency;
import com.tripoin.core.pojo.LinkedAccount;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.Role;
import com.tripoin.core.pojo.SystemParameter;
import com.tripoin.core.pojo.User;
import com.tripoin.core.rest.endpoint.api.bca.OAuthBCAApi;
import com.tripoin.core.rest.endpoint.api.bca.UserRegistrationBCAApi;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.soap.handler.WSEndpointFaultException;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.core.service.util.IVersionControlSystemTableService;
import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.request.DTORequestSignUpFacebook;
import com.tripoin.util.mail.ICoreMailSender;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("signUpFacebookEndpoint")
public class SignUpFacebookEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(SignUpFacebookEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private ISystemParameterService systemParameterService;
    
    @Autowired
    private IVersionControlSystemTableService versionControlSystemTableService;
	
	@Autowired
	private UserRegistrationBCAApi userRegistrationBCAApi;

	@Autowired
	private StandardStringDigester jasyptStringDigester;
	
	@Autowired
	private ICoreMailSender iCoreMailSender;

	@Autowired
	private OAuthBCAApi oauthBCAApi;
	
	@Autowired
    private ServletContext context;
	
	@Autowired
	@Qualifier(value="transactionManager")
	private PlatformTransactionManager transactionManager ;
	
	@Autowired
	@Qualifier(value="web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor; 

	@Value("${tripoin.email.from}")
	private String emailFrom;
	
	@Value("${path.image}")
	private String rootPath;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/signup/facebook</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<GeneralTransferObject> doRegisterAccount(Message<DTORequestSignUpFacebook<FacebookProfileData, CustomerData>> inMessage){
		GeneralTransferObject generalTransferObject = new GeneralTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		try {
			DTORequestSignUpFacebook<FacebookProfileData, CustomerData> dtoRequestSignUp = inMessage.getPayload();
			if(dtoRequestSignUp != null){
				FacebookProfileData facebookProfileData = dtoRequestSignUp.getFacebookProfileData();
				CustomerData customerData = dtoRequestSignUp.getCustomerData();
				List<Profile> profileListCheck = iGenericManagerJpa.loadObjectsJQLStatement(
						"SELECT prf FROM Profile prf WHERE prf.user.username = ? OR prf.email = ? OR prf.phone = ?",
						new Object[] { customerData.getEmail(), customerData.getEmail(), customerData.getPhoneNumber() }, null);
				if (profileListCheck != null && !profileListCheck.isEmpty()) {
					wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
    				throw new WSEndpointFaultException(EResponseCode.RC_PHONE_EXISTS.getResponseCode(), wsEndpointFault);
				}
				SystemParameter systemParameter = systemParameterService.getParameter(ParameterConstant.BCA_PARAM_COMPANY_CODE);
				DTORequestUserRegistrationBCA dtoRequestUserRegistrationBCA = new DTORequestUserRegistrationBCA();
				String customerName = facebookProfileData.getName();
				String mobileNo = customerData.getPhoneNumber();
				String companyCode = systemParameter.getValue();
				final String email = customerData.getEmail();
				dtoRequestUserRegistrationBCA.setCustomerName(customerName);
				dtoRequestUserRegistrationBCA.setPrimaryID(mobileNo);
				dtoRequestUserRegistrationBCA.setMobileNumber(mobileNo);
				dtoRequestUserRegistrationBCA.setEmailAddress(email);
				dtoRequestUserRegistrationBCA.setCompanyCode(companyCode);
				dtoRequestUserRegistrationBCA.setCustomerNumber(mobileNo);
				dtoRequestUserRegistrationBCA.setDateOfBirth("");
				DTOResponseUserRegistrationBCA dtoResponseUserRegistrationBCA = userRegistrationBCAApi.doUserRegistration(dtoRequestUserRegistrationBCA);
				if(dtoResponseUserRegistrationBCA.getErrorCode() != null){
					wsEndpointFault.setMessage(EResponseCode.RC_FAILURE.toString());
    				throw new WSEndpointFaultException(dtoResponseUserRegistrationBCA.getErrorMessage().getEnglish(), wsEndpointFault);					
				}
				final String username = customerData.getEmail();
			    String password = jasyptStringDigester.digest(customerData.getPassword());
			    final String name = facebookProfileData.getName();
			    String gender = facebookProfileData.getGender();
			    String address = customerData.getAddress();
			    final UUID uuid = UUID.randomUUID();
				
			    Role role = iGenericManagerJpa.loadObjectsFilterArgument(Role.class, 
						new FilterArgument[] { new FilterArgument("code", ECommonOperator.EQUALS)},
						new Object[]{customerData.getRoleCode()}, null, null).get(0);				
				iGenericManagerJpa.execQueryNotCriteria("INSERT INTO sec_user "
						+ "(user_username, user_password, user_enabled, user_status, role_id) VALUES "
						+ "(:username, :password, :enabled, :status, :role)",
						new ValueArgument[]{
						new ValueArgument("username", username),
						new ValueArgument("password", password),
						new ValueArgument("enabled", 1),
						new ValueArgument("status", 1),
						new ValueArgument("role", role.getId())
				});
				
				List<User> getUserId = iGenericManagerJpa.loadObjectsJQLStatement("FROM User WHERE username = ?", new Object[]{username}, null);
            	ValueArgument[] valueArgumentsProfile = new ValueArgument[]{
    					new ValueArgument("email", email),
    					new ValueArgument("name", name),
    					new ValueArgument("gender", gender),
    					new ValueArgument("birthPlace", null),
    					new ValueArgument("birthDate", null),
    					new ValueArgument("address", address),
    					new ValueArgument("homePhone", null),
    					new ValueArgument("user", getUserId.get(0).getId()),
    					new ValueArgument("mobilePhone", mobileNo),
    					new ValueArgument("resources", uuid.toString()),
    					new ValueArgument("createdBy", email),
    					new ValueArgument("createdIP", ParameterConstant.IP_ADDRESSV4_DEFAULT),
    					new ValueArgument("createdTime", new Date()),
    					new ValueArgument("createdPlatform", ParameterConstant.PLATFORM_DEFAULT)
    			};
            	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO mst_profile "
            			+ "(profile_email, profile_name, profile_gender, profile_birthplace, profile_birthdate, profile_address, "
            			+ "profile_telp, user_id, profile_phone, profile_resources_uuid, "
            			+ "created_by, created_ip, created_time, created_platform) "
            			+ "VALUES(:email, :name, :gender, :birthPlace, :birthDate, :address, "
            			+ ":homePhone, :user, :mobilePhone, :resources, "
            			+ ":createdBy, :createdIP, :createdTime, :createdPlatform)", valueArgumentsProfile);            	

            	List<Profile> getProfileId = iGenericManagerJpa.loadObjectsJQLStatement("FROM Profile WHERE email = ?", new Object[]{email}, null);
            	APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, 
            			new FilterArgument[]{new FilterArgument("code", ECommonOperator.EQUALS)}, 
    					new Object[]{APIConstant.BCA.getOperator()}, null, null).get(0);
            	ValueArgument[] valueArgumentsLinkedAccount = new ValueArgument[]{
    					new ValueArgument("code", facebookProfileData.getId()), new ValueArgument("name", facebookProfileData.getName()), new ValueArgument("username", facebookProfileData.getEmail()),
    					new ValueArgument("photo", facebookProfileData.getUrlPhoto()), new ValueArgument("token", dtoRequestSignUp.getAccessToken()), new ValueArgument("apiId", apiType.getId()),
    					new ValueArgument("profileId", getProfileId.get(0).getId()), new ValueArgument("status", 1), new ValueArgument("createdBy", email),
    					new ValueArgument("createdIP", ParameterConstant.IP_ADDRESSV4_DEFAULT),
    					new ValueArgument("createdTime", new Date()),
    					new ValueArgument("createdPlatform", ParameterConstant.PLATFORM_DEFAULT)
    			};
            	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO mst_linked_account( "+
            			"linked_account_code, linked_account_name, linked_account_username, "+
            			"linked_account_photo, linked_account_token, api_type_id, "+
            			"profile_id, status, created_by, "+
            			"created_ip, created_time, created_platform) "+
            			"VALUES ( "+
            			":code, :name, :username, "+
            			":photo, :token, :apiId, "+
            			":profileId, :status, :createdBy, "+
            			":createdIP, :createdTime, :createdPlatform)", valueArgumentsLinkedAccount);

            	Currency currency = iGenericManagerJpa.loadObjectsFilterArgument(Currency.class, 
            			new FilterArgument[]{new FilterArgument("code", ECommonOperator.EQUALS)}, 
    					new Object[]{"IDR"}, null, null).get(0);
            	ValueArgument[] valueArgumentsAccountFinance = new ValueArgument[]{
    					new ValueArgument("code", dtoRequestUserRegistrationBCA.getPrimaryID()), 
    					new ValueArgument("number", dtoRequestUserRegistrationBCA.getCustomerNumber()), 
    					new ValueArgument("balance", "0"),
    					new ValueArgument("authorization", ParameterConstant.BCA_WALLET_ACTIVE),
    					new ValueArgument("userId", getUserId.get(0).getId()),
    					new ValueArgument("currencyId", currency.getId()),
    					new ValueArgument("status", 1), new ValueArgument("createdBy", email),
    					new ValueArgument("createdIP", ParameterConstant.IP_ADDRESSV4_DEFAULT),
    					new ValueArgument("createdTime", new Date()),
    					new ValueArgument("createdPlatform", ParameterConstant.PLATFORM_DEFAULT)
    			};
            	iGenericManagerJpa.execQueryNotCriteria("INSERT INTO mst_account_finance( "+
            			"account_finance_code, account_finance_number, account_finance_balance, "+
            			"account_finance_authorization, user_id, currency_id, "+
            			"status, created_by, created_ip, created_time, created_platform) "+
            			"VALUES ( "+
            			":code, :number, :balance, "+
            			":authorization, :userId, :currencyId, "+
            			":status, :createdBy, :createdIP, :createdTime, :createdPlatform)", valueArgumentsAccountFinance);
            	
            	taskExecutor.execute(new Runnable() {			
        			@Override
        			public void run() {
        				final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        				transactionTemplate.execute(new TransactionCallback<Object>() {
        					@Override
        					public Object doInTransaction(TransactionStatus arg0) {
        						try {
        							versionControlSystemTableService.insertValueAndSync(LinkedAccount.TABLE_NAME, new Long(1), "Table of ".concat(LinkedAccount.TABLE_NAME));
        							
        							File tempDir = new File(context.getRealPath(rootPath.concat(uuid.toString())));
        		                    if(!tempDir.exists())
        		        				tempDir.mkdirs();
        		                    
        		                    List<SystemParameter> systemParameters = systemParameterService.listValue(new Object[]{ParameterConstant.NEW_USER_SUBJECT, ParameterConstant.NEW_USER_BODY});
        						    Map<String, String> mapSystemParamter = new HashMap<String, String>();
        						    for(SystemParameter systemParameter : systemParameters)
        						    	mapSystemParamter.put(systemParameter.getCode(), systemParameter.getValue());
        						    String content = mapSystemParamter.get(ParameterConstant.NEW_USER_BODY);
        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_FULLNAME, name);
        						    content = content.replaceAll(ParameterConstant.TRIPOIN_CONTENT_USERNAME, username);
        		                    iCoreMailSender.sendMailContent(emailFrom, email, mapSystemParamter.get(ParameterConstant.NEW_USER_SUBJECT), content);
        						} catch (Exception e) {
        							LOGGER.error("Sign Up Account Facebook System Error : " + e.getLocalizedMessage(), e);
        						}
        						return null;
        					}
        				});
        			}
        		});
				generalTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
				generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				generalTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
				profileListCheck = null;
			}
		} catch (WSEndpointFaultException e) {
			LOGGER.error("Sign Up Account Facebook System Error : "+e.getMessage(), e);
			generalTransferObject.setResponseCode(e.getMessage());
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc(e.getFaultInfo().getMessage());
        }  catch (Exception e) {
			LOGGER.error("Sign Up Account Facebook System Error : "+e.getMessage(), e);
			generalTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			generalTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			generalTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
		}
		setReturnStatusAndMessage(generalTransferObject, responseHeaderMap);
		Message<GeneralTransferObject> message = new GenericMessage<GeneralTransferObject>(generalTransferObject, responseHeaderMap);
		generalTransferObject = null;
		return message;	
	}
	
	/*SystemParameter systemParameter = systemParameterService.getParameter(ParameterConstant.BCA_PARAM_COMPANY_CODE);
	DTORequestUserRegistrationBCA dtoRequestUserRegistrationBCA = new DTORequestUserRegistrationBCA();
	String customerName = "Ridla Fadilah";
	String mobileNo = "081234567123";
	String companyCode = systemParameter.getValue();
	String email = "ridla.fadilah@gmail.com";
	dtoRequestUserRegistrationBCA.setCustomerName(customerName);
	dtoRequestUserRegistrationBCA.setPrimaryID(mobileNo);
	dtoRequestUserRegistrationBCA.setMobileNumber(mobileNo);
	dtoRequestUserRegistrationBCA.setEmailAddress(email);
	dtoRequestUserRegistrationBCA.setCompanyCode(companyCode);
	dtoRequestUserRegistrationBCA.setCustomerNumber(mobileNo);
	dtoRequestUserRegistrationBCA.setDateOfBirth("");
	System.out.println(userRegistrationBCAApi.doUserRegistration(dtoRequestUserRegistrationBCA));*/
	
}
