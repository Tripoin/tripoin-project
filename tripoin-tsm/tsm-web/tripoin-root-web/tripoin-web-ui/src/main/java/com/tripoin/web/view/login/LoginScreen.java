package com.tripoin.web.view.login;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.web.authentication.IAccessControl;
import com.tripoin.web.common.EWebUIConstant;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * UI content when the user is not logged in yet.
 * 
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class LoginScreen extends CssLayout implements View {

	private static final long serialVersionUID = -1126309038760546247L;
	private TextField username;
    private PasswordField password;
    private Button login;
    private Button forgotPassword;
    private LoginListener loginListener;
    
    @Autowired
    private IAccessControl accessControl;

    public LoginScreen() {
        buildUI();
        username.focus();
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
    }

    private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

        loginForm.addComponent(username = new TextField("Username"));
        username.setWidth(15, Unit.EM);
        username.setMaxLength(15);
        loginForm.addComponent(password = new PasswordField("Password"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Write anything");
        password.setMaxLength(50);
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Login"));
        login.setDisableOnClick(true);
        login.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 53728288770839955L;

			@Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        buttons.addComponent(forgotPassword = new Button("Forgot password?"));
        forgotPassword.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -2432274293955123347L;
			@Override
            public void buttonClick(Button.ClickEvent event) {
                showNotification(new Notification("Hint: Try anything"));
            }
        });
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        return loginForm;
    }

    private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label("<h1>Login Information</h1>"
        		+ "Logging On is the process by which individual access to a computer system is controlled by identifying and authenticating the user through the credentials presented by the user.", ContentMode.HTML);
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }

    private void login() {
    	if(username.getValue() == null || "".equals(username.getValue()) || username.getValue().isEmpty()){
            showNotification(new Notification(EWebUIConstant.LOGIN_FAILED_TITLE.toString(), EWebUIConstant.LOGIN_USERNAME_NULL_DESC.toString(), 
            		Notification.Type.HUMANIZED_MESSAGE));
            username.focus();		
    	}else if(password.getValue() == null || "".equals(password.getValue()) || password.getValue().isEmpty()){
            showNotification(new Notification(EWebUIConstant.LOGIN_FAILED_TITLE.toString(), EWebUIConstant.LOGIN_PASSWORD_NULL_DESC.toString(), 
            		Notification.Type.HUMANIZED_MESSAGE));
            password.focus();		
    	}else if(!username.getValue().matches(EWebUIConstant.REGEX_USERNAME.toString())){
            showNotification(new Notification(EWebUIConstant.LOGIN_FAILED_TITLE.toString(), EWebUIConstant.LOGIN_FAILED_DESC.toString(),
                    Notification.Type.HUMANIZED_MESSAGE));
            username.focus();
		}else{
            if (accessControl.signIn(username.getValue(), password.getValue())) {
            	loginListener.loginSuccessful();
            }else{
                showNotification(new Notification(EWebUIConstant.LOGIN_FAILED_TITLE.toString(), EWebUIConstant.LOGIN_FAILED_DESC.toString(),
                        Notification.Type.HUMANIZED_MESSAGE));
                username.focus();
            }
        }
    }

    private void showNotification(Notification notification) {
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	public void addLoginListener(LoginListener loginListener){
		this.loginListener = loginListener;
	}
	
    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }
    
}
