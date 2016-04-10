package com.tripoin.web.view.login;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.tripoin.web.authentication.IAccessControl;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.view.security.ForgotPasswordScreen;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
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
	private final Panel panel = new Panel();
	private final TabSheet tabs = new TabSheet();
	private TextField username;
    private PasswordField password;
    private Button login;
    private Button forgotPassword;
    private LoginListener loginListener;
    private String urlFacebook;
    private String clientIdFacebook;
    private String callbackFacebook;
    private String scopesFacebook;
    
    @Autowired
    private IAccessControl accessControl;
    
    @Autowired
    private ForgotPasswordScreen forgotPasswordScreen;
    
    @Value("${facebook.api.auth.url}")
    public void setUrlFacebook(String urlFacebook) {
		this.urlFacebook = urlFacebook;
	}

    @Value("${facebook.client.id}")
	public void setClientIdFacebook(String clientIdFacebook) {
		this.clientIdFacebook = clientIdFacebook;
	}

    @Value("${facebook.callback.url}")
	public void setCallbackFacebook(String callbackFacebook) {
		this.callbackFacebook = callbackFacebook;
	}

    @Value("${facebook.scopes}")
	public void setScopesFacebook(String scopesFacebook) {
		this.scopesFacebook = scopesFacebook;
	}

	@PostConstruct
    public void init() throws Exception {
        buildUI();
        username.focus();
    }

	private void buildUI() {
        addStyleName("login-screen");

        Component signInTab = buildSignInForm();
        Component signUpTab = panelSignUp();

        tabs.setWidth("400px");
        tabs.addStyleName("login-tabs");
        tabs.addStyleName("framed equal-width-tabs");
    	tabs.addTab(signInTab, "Login");
    	tabs.addTab(signUpTab, "Register");
        
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(tabs);
        centeringLayout.setComponentAlignment(tabs, Alignment.MIDDLE_CENTER);

        CssLayout loginInformation = buildLoginInformation();

        addComponent(centeringLayout);
        addComponent(loginInformation);
    }
	
	private Component panelSignUp(){
        panel.addStyleName("borderless");
        panel.addStyleName("scroll-divider");
        panel.setHeight("216px");
        panel.setContent(buildSocMedForm());
		return panel;
	}
    
    private Component buildSocMedForm() {        
        VerticalLayout signUpLayout = new VerticalLayout();
        signUpLayout.setMargin(true);
        signUpLayout.setSpacing(true);
        
        Button facebookButton = new Button("register using Facebook");
        Resource facebookIco = new ThemeResource("img/Facebook.svg");
        facebookButton.setIcon(facebookIco);
        facebookButton.addStyleName("borderless-colored");
        signUpLayout.addComponent(facebookButton);
        facebookButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -534324766257259614L;
			@Override
			public void buttonClick(ClickEvent event) {
				urlFacebook = urlFacebook.concat("client_id=").concat(clientIdFacebook)
						.concat("&response_type=").concat("code")
						.concat("&redirect_uri=").concat(callbackFacebook)
						.concat("&scope=").concat(scopesFacebook)
						.concat("&state=").concat(UUID.randomUUID().toString());
				Page.getCurrent().open(urlFacebook, "_top");
			}
		});

        Button twitterButton = new Button("register using Twitter");
        Resource twitterIco = new ThemeResource("img/Twitter.svg");
        twitterButton.setIcon(twitterIco);
        twitterButton.addStyleName("borderless-colored");
        signUpLayout.addComponent(twitterButton);

        Button instagramButton = new Button("register using Instagram");
        Resource instagramIco = new ThemeResource("img/Instagram.svg");
        instagramButton.setIcon(instagramIco);
        instagramButton.addStyleName("borderless-colored");
        signUpLayout.addComponent(instagramButton);

        Button googlePlusButton = new Button("register using Google Plus");
        Resource googlePlusIco = new ThemeResource("img/GooglePlus.svg");
        googlePlusButton.setIcon(googlePlusIco);
        googlePlusButton.addStyleName("borderless-colored");
        signUpLayout.addComponent(googlePlusButton);
        return signUpLayout;
    }

    private Component buildSignInForm() {
        FormLayout loginForm = new FormLayout();
        loginForm.setMargin(true);
        loginForm.setSpacing(true);
        loginForm.addStyleName("login-form");
        
        loginForm.addComponent(username = new TextField("Username"));
        username.setWidth(15, Unit.EM);
        username.setDescription("Username Or Email Or Phone Number");
        username.setMaxLength(55);
        loginForm.addComponent(password = new PasswordField("Password"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Password");
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
        forgotPasswordScreen.setLoginScreen(this);
        forgotPassword.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -2432274293955123347L;
			@Override
            public void buttonClick(Button.ClickEvent event) {	
				username.setValue("");
				password.setValue("");
	        	Page.getCurrent().setTitle("Tripoin Forgot Password");
	            getUI().setContent(forgotPasswordScreen);
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
        notification.setDelayMsec(1500);
        notification.show(Page.getCurrent());
    }

	@Override
	public void enter(ViewChangeEvent event) {
    	System.out.println(getUI().getPage().getLocation().getQuery());		
	}
	
	public void addLoginListener(LoginListener loginListener){
		this.loginListener = loginListener;
	}
	
    public interface LoginListener extends Serializable {
        void loginSuccessful();
    }
    
}
