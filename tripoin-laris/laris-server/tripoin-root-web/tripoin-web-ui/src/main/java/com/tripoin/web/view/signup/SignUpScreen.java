package com.tripoin.web.view.signup;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.request.DTORequestSignUpFacebook;
import com.tripoin.dto.response.DTOResponseCallbackFacebook;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IForgotPasswordService;
import com.tripoin.web.service.ISignUpService;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SignUpScreen extends CssLayout implements View {

	private static final long serialVersionUID = -1126309038760546247L;
	
	@Autowired
	private IForgotPasswordService forgotPasswordService;
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private ISignUpService signUpService;

	private final TabSheet tabs = new TabSheet();
	private Tab tab1;
	private Tab tab2;
	private PasswordField passwordTextField;
	private PasswordField reTypePasswordTextField;
	private TextField phoneNumberTextField;
	private TextField emailTextField;
	private TextArea addressTextField;
    private OptionGroup customerOptionGroup;
    private TextField accountNumberTextField;
	private TextField captchaTextField;
    private String captchaPlainText;
    private Embedded reloadCaptcha;
    private VerticalLayout groupCaptcha;
    private CheckBox termsOfServiceCheckBox;
    private Button nextButton;
    private Button previousButton;
    private Button registerButton;
    private Button cancelButton;
    private Notification notificationAfterSend = new Notification("");

    @PostConstruct
    public void init() throws Exception {
        buildUI();
        passwordTextField.focus();
    }

    private void buildUI() {
        addStyleName("login-screen");
    	Component signUpForm1 = buildSignUpForm1();
    	Component signUpForm2 = buildSignUpForm2();

        tabs.setWidth("400px");
        tabs.addStyleName("login-tabs");
        tabs.addStyleName("framed equal-width-tabs");
    	tab1 = tabs.addTab(signUpForm1, "Step 1");
    	tab2 = tabs.addTab(signUpForm2, "Step 2");
    	tab2.setEnabled(false);
    	
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(tabs);
        centeringLayout.setComponentAlignment(tabs, Alignment.MIDDLE_CENTER);

        CssLayout loginInformation = buildSignUpInformation();

        addComponent(centeringLayout);
        addComponent(loginInformation);
        
		notificationAfterSend.setStyleName("system dark small closable");
        notificationAfterSend.setPosition(Position.BOTTOM_CENTER);
        notificationAfterSend.setDelayMsec(5000);
    }

    private Component buildSignUpForm1() {
        FormLayout signUpForm = new FormLayout();
        signUpForm.addStyleName("login-form");
        signUpForm.setSizeUndefined();
        signUpForm.setMargin(false);

        signUpForm.addComponent(passwordTextField = new PasswordField("Password"));
        passwordTextField.setWidth(12, Unit.EM);
        passwordTextField.setDescription("Password");
        passwordTextField.setMaxLength(30);
        passwordTextField.setRequired(true);
        signUpForm.addComponent(reTypePasswordTextField = new PasswordField("Re-Type Password"));
        reTypePasswordTextField.setWidth(12, Unit.EM);
        reTypePasswordTextField.setDescription("Re-Type Password");
        reTypePasswordTextField.setMaxLength(30);
        reTypePasswordTextField.setRequired(true);
        signUpForm.addComponent(phoneNumberTextField = new TextField("Phone No."));
        phoneNumberTextField.setWidth(12, Unit.EM);
        phoneNumberTextField.setDescription("Phone No.");
        phoneNumberTextField.setMaxLength(15);
        phoneNumberTextField.setRequired(true);
        signUpForm.addComponent(emailTextField = new TextField("Email"));
        emailTextField.setWidth(12, Unit.EM);
        emailTextField.setDescription("Email");
        emailTextField.setMaxLength(55);
        emailTextField.setRequired(true);
        signUpForm.addComponent(addressTextField = new TextArea("Address"));
        addressTextField.setWidth(12, Unit.EM);
        addressTextField.setHeight(5, Unit.EM);
        addressTextField.setMaxLength(150);
        addressTextField.setDescription("Address");
        addressTextField.setRequired(true);
        
        CssLayout buttons = new CssLayout();
        /*buttons.setStyleName("buttons");*/
        buttons.setStyleName("v-csslayout-margin");
        buttons.setSizeUndefined();
        signUpForm.addComponent(buttons); 
        CssLayout button1 = new CssLayout();
        button1.setStyleName("v-csslayout-margin");
        button1.setSizeUndefined();
        button1.addComponent(cancelButton = new Button("Cancel"));
        buttons.addComponent(button1);
        cancelButton.setDisableOnClick(true);
        cancelButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                	reloadCaptcha.setSource(generateCaptcha());
                	passwordTextField.setValue("");
                	reTypePasswordTextField.setValue("");
                	addressTextField.setValue("");
                	emailTextField.setValue("");
                	accountNumberTextField.setValue("");
                	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_API_FACEBOOK_DATA.toString());
                	Page.getCurrent().setLocation("/");
                } finally {
                	cancelButton.setEnabled(true);
                }
            }
        });
        cancelButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancelButton.addStyleName(ValoTheme.BUTTON_DANGER);
        CssLayout button2 = new CssLayout();
        button2.setStyleName("v-csslayout-margin");
        button2.setSizeUndefined();
        button2.addComponent(nextButton = new Button("Next"));
        buttons.addComponent(button2);
        nextButton.setDisableOnClick(true);
        nextButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
    				reloadCaptcha.setSource(generateCaptcha());
                	if(!validateForm1()){
                		notificationAfterSend.setCaption("Error");
                		notificationAfterSend.setDescription("Register account error, please try again later!");
                		notificationAfterSend.show(Page.getCurrent());
                	}else{
                		tab1.setEnabled(false);
                		tab2.setEnabled(true);
                		tabs.setSelectedTab(tab2);
                	}
                } finally {
                    nextButton.setEnabled(true);
                }
            }
        });
        nextButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        nextButton.addStyleName(ValoTheme.BUTTON_PRIMARY);        
        
        return signUpForm;
    }


    private Component buildSignUpForm2() {
        FormLayout signUpForm = new FormLayout();
        signUpForm.addStyleName("login-form");
        signUpForm.setSizeUndefined();
        signUpForm.setMargin(false);
        
        signUpForm.addComponent(customerOptionGroup = new OptionGroup("Customer Type"));
        customerOptionGroup.addItem(ParameterConstant.BUYER);
        customerOptionGroup.addItem(ParameterConstant.SELLER);
        customerOptionGroup.select(ParameterConstant.BUYER);
        customerOptionGroup.addStyleName("horizontal");
        customerOptionGroup.setRequired(true);
        customerOptionGroup.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1787151101692045729L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(ParameterConstant.SELLER.equals(event.getProperty().getValue())){
					accountNumberTextField.setVisible(true);
				}else{
					accountNumberTextField.setVisible(false);
				}
			}
		});
        signUpForm.addComponent(accountNumberTextField = new TextField("Account No."));
        accountNumberTextField.setWidth(15, Unit.EM);
        accountNumberTextField.setMaxLength(20);
        accountNumberTextField.setDescription("Account No.");
        accountNumberTextField.setRequired(true);
        accountNumberTextField.setVisible(false);
        signUpForm.addComponent(groupCaptcha = new VerticalLayout());
        groupCaptcha.setWidth(15, Unit.EM);
        groupCaptcha.setSpacing(true);
        reloadCaptcha = new Embedded(null, generateCaptcha());
        reloadCaptcha.setWidth(15, Unit.EM);
        reloadCaptcha.setHeight(5, Unit.EM);
        reloadCaptcha.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -8643020125552543352L;
			@Override
			public void click(ClickEvent event) {
				reloadCaptcha.setSource(generateCaptcha());
			}
		});
        captchaTextField = new TextField("Click Image to Reload");
        captchaTextField.setWidth(15, Unit.EM);
        captchaTextField.setMaxLength(5);
        groupCaptcha.addComponent(reloadCaptcha);
        groupCaptcha.addComponent(captchaTextField);
        signUpForm.addComponent(termsOfServiceCheckBox = new CheckBox("I aggre to the Laris Terms of Service", false));
        termsOfServiceCheckBox.setWidth(15, Unit.EM);
        termsOfServiceCheckBox.setRequired(true);
        
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        buttons.setSizeUndefined();   
        signUpForm.addComponent(buttons);  
        CssLayout button1 = new CssLayout();
        button1.setStyleName("v-csslayout-margin");
        button1.setSizeUndefined();
        button1.addComponent(previousButton = new Button("Previous"));
        buttons.addComponent(button1);
        previousButton.setDisableOnClick(true);
        previousButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
            		tab2.setEnabled(false);
            		tab1.setEnabled(true);
                    tabs.setSelectedTab(tab1);
                } finally {
                	previousButton.setEnabled(true);
                }
            }
        });  
        previousButton.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        previousButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        CssLayout button2 = new CssLayout();
        button2.setStyleName("v-csslayout-margin");
        button2.setSizeUndefined();
        button2.addComponent(registerButton = new Button("Register"));
        buttons.addComponent(button2);
        registerButton.setDisableOnClick(true);
        registerButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                	sendSignUpData();
                } finally {
                    registerButton.setEnabled(true);
                }
            }
        });
        registerButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        registerButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);        
        
        return signUpForm;
    }

    private CssLayout buildSignUpInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label("<h1>Register</h1>"
        		+ "One Account for all your Social Media<br>"
        		+ "Enter your password, email, and others data.<br><br>"
        		+ "Keep me logged in.", ContentMode.HTML);
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }
    
    private void sendSignUpData() {
    	boolean validateForm = true;
		accountNumberTextField.setComponentError(null);
		captchaTextField.setComponentError(null);
		termsOfServiceCheckBox.setComponentError(null);
		validateForm = validateForm1();
		if(!validateForm)
			tabs.setSelectedTab(tab1);
    	if (accountNumberTextField.isVisible() && !accountNumberTextField.getValue().matches(EWebUIConstant.REGEX_ACCOUNT_NUMBER.toString())) {
    		accountNumberTextField.setComponentError(new UserError("Field Account No. not valid"));
    		validateForm = false;
    	}
    	if(!captchaPlainText.equals(captchaTextField.getValue())){
    		captchaTextField.setComponentError(new UserError("Captcha not valid"));
    		validateForm = false;
    	}
    	if(!termsOfServiceCheckBox.getValue()){
    		termsOfServiceCheckBox.setComponentError(new UserError("Terms Of Service must be agree"));
    		validateForm = false;
    	}
    	if(validateForm){
        	DTOResponseCallbackFacebook dtoResponseCallbackFacebook = (DTOResponseCallbackFacebook)VaadinSession.getCurrent().getSession().getAttribute(EWebSessionConstant.SESSION_API_FACEBOOK_DATA.toString());
        	DTORequestSignUpFacebook<FacebookProfileData, CustomerData> dtoRequestSignUp = new DTORequestSignUpFacebook<FacebookProfileData, CustomerData>();
        	dtoRequestSignUp.setFacebookProfileData(dtoResponseCallbackFacebook.getFacebookProfileData());
        	dtoRequestSignUp.setAccessToken(dtoResponseCallbackFacebook.getAccessToken());
        	dtoRequestSignUp.setState(dtoResponseCallbackFacebook.getState());
        	CustomerData customerData = new CustomerData();
        	if(accountNumberTextField.isVisible())
        		customerData.setAccountNumber(accountNumberTextField.getValue());
        	customerData.setAddress(addressTextField.getValue());
        	customerData.setEmail(emailTextField.getValue());
        	customerData.setPassword(passwordTextField.getValue());
        	customerData.setPhoneNumber(phoneNumberTextField.getValue());
        	if(ParameterConstant.SELLER.equals(customerOptionGroup.getValue().toString())){
        		customerData.setRoleCode(RoleConstant.ROLE_SELLER);
        	}else{
        		customerData.setRoleCode(RoleConstant.ROLE_BUYER);
        	}
        	dtoRequestSignUp.setCustomerData(customerData);
        	GeneralTransferObject generalTransferObject = signUpService.registerWithFacebook(dtoRequestSignUp);
        	if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
        		notificationAfterSend.setCaption("Success");
        		notificationAfterSend.setDescription(dtoRequestSignUp.toString());
        		notificationAfterSend.show(Page.getCurrent());
            	VaadinSession.getCurrent().getSession().removeAttribute(EWebSessionConstant.SESSION_API_FACEBOOK_DATA.toString());
        		Page.getCurrent().setLocation("/");        		
        	}else{
        		reloadCaptcha.setSource(generateCaptcha());
        		notificationAfterSend.setCaption("Error");
        		notificationAfterSend.setDescription("Register account error, please try again later!");
        		notificationAfterSend.show(Page.getCurrent());        		
        	}
    	}else{
    		reloadCaptcha.setSource(generateCaptcha());
    		notificationAfterSend.setCaption("Error");
    		notificationAfterSend.setDescription("Register account error, please try again later!");
    		notificationAfterSend.show(Page.getCurrent());
    	}
    }
    
    private boolean validateForm1(){
    	boolean validateForm = true;
		passwordTextField.setComponentError(null);
		reTypePasswordTextField.setComponentError(null);
		phoneNumberTextField.setComponentError(null);
		emailTextField.setComponentError(null);
		addressTextField.setComponentError(null);
    	if(passwordTextField.getValue() == null && passwordTextField.getValue().isEmpty()){
    		passwordTextField.setComponentError(new UserError("Field Password not null"));
    		validateForm = false;
    	}
    	if(!passwordTextField.getValue().matches(EWebUIConstant.REGEX_CONTAINS_DIGIT.toString())){
    		passwordTextField.setComponentError(new UserError("Field Password not valid"));
    		validateForm = false;
    	}
    	if(reTypePasswordTextField.getValue() == null && reTypePasswordTextField.getValue().isEmpty()){
    		reTypePasswordTextField.setComponentError(new UserError("Field Re-Type Password not null"));
    		validateForm = false;
    	}
    	if(!passwordTextField.getValue().equals(reTypePasswordTextField.getValue())){
    		passwordTextField.setComponentError(new UserError("Field Password must be same with Field Re-Type Password"));
    		reTypePasswordTextField.setComponentError(new UserError("Field Re-Type Password must be same with Field Password"));
    		validateForm = false;
    	}
    	if(!phoneNumberTextField.getValue().matches(EWebUIConstant.REGEX_PHONE_NUMBER.toString())){
    		phoneNumberTextField.setComponentError(new UserError("Field Phone No. not valid"));
    		validateForm = false;
    	}
    	if (!emailTextField.getValue().matches(EWebUIConstant.REGEX_EMAIL.toString())) {
    		emailTextField.setComponentError(new UserError(EWebUIConstant.NOTIF_EMAIL_NOTVALID_FORGOT_PASSWORD_DESC.toString()));
    		validateForm = false;
    	}
    	if (addressTextField.getValue().length() <= 10) {
    		addressTextField.setComponentError(new UserError("Field Address not valid"));
    		validateForm = false;
    	}
    	return validateForm;
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	public StreamResource generateCaptcha(){
		VaadinService.getCurrentResponse().setDateHeader("Expires", 0);
    	VaadinService.getCurrentResponse().setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    	VaadinService.getCurrentResponse().setHeader("Cache-Control", "post-check=0, pre-check=0");
    	VaadinService.getCurrentResponse().setHeader("Pragma", "no-cache");
    	VaadinService.getCurrentResponse().setContentType("image/jpeg");
    	captchaPlainText = captchaProducer.createText();
    	VaadinSession.getCurrent().setAttribute(Constants.KAPTCHA_SESSION_KEY, captchaPlainText);
        StreamResource.StreamSource captchaImageStream = new StreamResource.StreamSource(){
			private static final long serialVersionUID = 4205683823302736202L;
			@Override
			public InputStream getStream() {
		        BufferedImage bufferedImage = captchaProducer.createImage(captchaPlainText);
		        ByteArrayOutputStream imageInputStream = new ByteArrayOutputStream();		        
		        try {
					ImageIO.write(bufferedImage, "jpg", imageInputStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return new ByteArrayInputStream(imageInputStream.toByteArray());
			}        	
        };
        return new StreamResource(captchaImageStream, "kaptcha.jpg");    	
    }
	
}
