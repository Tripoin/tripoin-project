package com.tripoin.web.view.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.service.IForgotPasswordService;
import com.tripoin.web.view.login.LoginScreen;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ForgotPasswordScreen extends CssLayout implements View {

	private static final long serialVersionUID = -1126309038760546247L;
	
	@Autowired
	private IForgotPasswordService forgotPasswordService;
	
	private TextField email;
	private TextField reTypeEmail;
    private Button send;
    private Button cancel;
    private Notification notificationAfterSend = new Notification("");
    
    private LoginScreen loginScreen;
    
    public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}

    @PostConstruct
    public void init() throws Exception {
        buildUI();
        email.focus();
    }

    private void buildUI() {
        addStyleName("login-screen");

        // login form, centered in the available part of the screen
        Component loginForm = buildLoginForm();

        // layout to center login form when there is sufficient screen space
        // - see the theme for how this is made responsive for various screen
        // sizes
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        // information text about logging in
        CssLayout loginInformation = buildLoginInformation();

        addComponent(centeringLayout);
        addComponent(loginInformation);
        
		notificationAfterSend.setStyleName("system closable");
        notificationAfterSend.setPosition(Position.BOTTOM_CENTER);
        notificationAfterSend.setDelayMsec(5000);
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();
        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

        loginForm.addComponent(email = new TextField("Email"));
        email.setWidth(15, Unit.EM);
        email.setDescription("Email");
        email.setMaxLength(55);
        loginForm.addComponent(reTypeEmail = new TextField("Re-Type Email"));
        reTypeEmail.setWidth(15, Unit.EM);
        reTypeEmail.setDescription("Re-Type Email");
        reTypeEmail.setMaxLength(55);
        
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);      
        buttons.addComponent(send = new Button("Send"));
        send.setDisableOnClick(true);
        send.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    sendEmailForgotPassword();
                } finally {
                    send.setEnabled(true);
                }
            }
        });
        send.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        send.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        
        buttons.addComponent(cancel = new Button("Keep me logged in."));
        cancel.setDisableOnClick(true);
        cancel.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 53728288770839955L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                	email.setValue("");
                	reTypeEmail.setValue("");
                	Page.getCurrent().setTitle("Tripoin Login");
    	            getUI().setContent(loginScreen);
                } finally {
                	cancel.setEnabled(true);
                }
            }
        });
        cancel.addStyleName(ValoTheme.BUTTON_LINK);
        
        return loginForm;
    }

    private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label("<h1>Forgot Password</h1>"
        		+ "Forgot your password?<br>"
        		+ "Enter your email address and we'll help you reset your password.<br><br>"
        		+ "Find Your Account.<br>"
        		+ "Keep me logged in.", ContentMode.HTML);
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }

    private void sendEmailForgotPassword() {
    	if(email.getValue().equals(reTypeEmail.getValue())){
    		if(email.getValue().matches(EWebUIConstant.REGEX_EMAIL.toString())){
        		GeneralTransferObject generalTransferObject = forgotPasswordService.forgotPassword(email.getValue());
        		if("0".equals(generalTransferObject.getResponseCode())){    			
        			notificationAfterSend.setCaption(EWebUIConstant.NOTIF_SUCCESS_FORGOT_PASSWORD_TITLE.toString());
                    notificationAfterSend.setDescription(EWebUIConstant.NOTIF_SUCCESS_FORGOT_PASSWORD_DESC.toString());
            		notificationAfterSend.show(Page.getCurrent());
            		email.setValue("");
                	reTypeEmail.setValue("");        		
                	Page.getCurrent().setTitle("Tripoin Login");
                    getUI().setContent(loginScreen);
        		}else if("2".equals(generalTransferObject.getResponseCode())){    			
        			notificationAfterSend.setCaption(EWebUIConstant.NOTIF_FAILURE_FORGOT_PASSWORD_TITLE.toString());
                    notificationAfterSend.setDescription(EWebUIConstant.NOTIF_ACOUNT_ENABLED_FORGOT_PASSWORD_DESC.toString());
            		notificationAfterSend.show(Page.getCurrent());  
            		email.setValue("");
                	reTypeEmail.setValue("");    		
                	Page.getCurrent().setTitle("Tripoin Login");
                    getUI().setContent(loginScreen);    			
        		}else if("3".equals(generalTransferObject.getResponseCode())){    			
        			notificationAfterSend.setCaption(EWebUIConstant.NOTIF_FAILURE_FORGOT_PASSWORD_TITLE.toString());
                    notificationAfterSend.setDescription(EWebUIConstant.NOTIF_ACCOUNT_EXPIRED_FORGOT_PASSWORD_DESC.toString());
            		notificationAfterSend.show(Page.getCurrent());  
                	email.setValue("");
                	reTypeEmail.setValue("");      		
                	Page.getCurrent().setTitle("Tripoin Login");
                    getUI().setContent(loginScreen);    			
        		}else {    			
        			showNotification(new Notification(EWebUIConstant.NOTIF_EMAIL_FAILURE_FORGOT_PASSWORD_TITLE.toString(), EWebUIConstant.NOTIF_EMAIL_NOTVALID_FORGOT_PASSWORD_DESC.toString(),
                            Notification.Type.HUMANIZED_MESSAGE));
                    email.focus(); 			
        		}    			
    		}else{
    			showNotification(new Notification(EWebUIConstant.NOTIF_EMAIL_FAILURE_FORGOT_PASSWORD_TITLE.toString(), EWebUIConstant.NOTIF_EMAIL_NOTVALID_FORGOT_PASSWORD_DESC.toString(),
                        Notification.Type.HUMANIZED_MESSAGE));
                email.focus();
    		}
    	}else{
    		showNotification(new Notification(EWebUIConstant.NOTIF_EMAIL_FAILURE_FORGOT_PASSWORD_TITLE.toString(), EWebUIConstant.LOGIN_FAILED_DESC.toString(),
                    Notification.Type.HUMANIZED_MESSAGE));
    		email.focus();
    	}
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}

    private void showNotification(Notification notification) {
        notification.setDelayMsec(1500);
        notification.show(Page.getCurrent());
    }
    
}
