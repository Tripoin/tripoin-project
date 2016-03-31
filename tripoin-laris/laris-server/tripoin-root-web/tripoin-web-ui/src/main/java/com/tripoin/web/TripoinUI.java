package com.tripoin.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.MenuData;
import com.tripoin.web.authentication.IAccessControl;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IForgotPasswordService;
import com.tripoin.web.service.ILogoutService;
import com.tripoin.web.servlet.DiscoveryNavigator;
import com.tripoin.web.view.error.ErrorView;
import com.tripoin.web.view.home.HomeView;
import com.tripoin.web.view.login.LoginScreen;
import com.tripoin.web.view.login.LoginScreen.LoginListener;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedHttpSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Tripoin UI class of the application that shows either the login screen or the
 * main view of the application depending on whether a user is signed in.
 * 
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Viewport("user-scalable=no,initial-scale=1.0")
@Widgetset("com.tripoin.web.TripoinWidgetSet")
@Theme("tripoin-base")
@Title("")
@PreserveOnRefresh
public class TripoinUI extends UI implements ErrorHandler {
	
	private static final long serialVersionUID = -57029129041123227L;
	private static transient final Logger LOGGER = LoggerFactory.getLogger(TripoinUI.class);
    private CssLayout menuItemsLayout;
    private ComponentContainer viewDisplay;
    private DiscoveryNavigator navigator;
    private ApplicationContext applicationContext;
    
    @Autowired
    private ErrorView errorView;
    
    @Autowired
    private LoginScreen loginScreen;
	
	@Autowired
    private IAccessControl accessControl;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	@Autowired
	private ILogoutService logoutService;
	
	@Autowired
	private IForgotPasswordService forgotPasswordService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	initializedScreen(vaadinRequest);
    }
    
    @Override
	protected void refresh(VaadinRequest vaadinRequest) {
		super.refresh(vaadinRequest);
		initializedScreen(vaadinRequest);
	}

	private void initializedScreen(VaadinRequest vaadinRequest){
    	getSession().setErrorHandler(this);
        Responsive.makeResponsive(this);
        setLocale(Locale.US);
        initLogin();
    }
	
	protected void initLogin() {
		removeStyleName(ValoTheme.UI_WITH_MENU);
    	loginScreen.addLoginListener(new LoginListener() {
			private static final long serialVersionUID = 5327649431527930757L;
			@Override
			public void loginSuccessful() {
				
			}
		});
    	getPage().setTitle("Tripoin Login");
        setContent(loginScreen);
	}

	@Override
	public void close() {
		if(getContent() == null || !loginScreen.equals(getContent())){
			if(SecurityContextHolder.getContext().getAuthentication() != null){
				logoutService.doLogout();	
				if(stateFullRest != null && stateFullRest.getAdditionalDataMenu() != null && !stateFullRest.getAdditionalDataMenu().isEmpty())
					stateFullRest.clearAllCookies();
			}
			SecurityContextHolder.clearContext();			
			getSession().close();
			UI.getCurrent().getPage().setLocation("j_spring_security_logout");
		}		
	}

	@Override
	public void error(com.vaadin.server.ErrorEvent event) {
        StringWriter errors = new StringWriter();
        event.getThrowable().printStackTrace(new PrintWriter(errors));
    	LOGGER.error("Fault Exception Tripoin UI", event.getThrowable());
        if("true".equals(VaadinServlet.getCurrent().getServletContext().getInitParameter("productionMode"))){
        	errorView.setDescription(ErrorView.VIEW_NAME);
        }else{
        	errorView.setDescription(errors.toString());        	
        }
        navigator.navigateTo(ErrorView.VIEW_NAME);
		Notification notification = new Notification("Bad Request", ErrorView.DESCRIPTION);
		notification.setStyleName("dark small closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(7500);
        if(Page.getCurrent() == null)
        	notification.show(getPage());
        else
        	notification.show(Page.getCurrent());
	}

    public static TripoinUI get() {
        return (TripoinUI) UI.getCurrent();
    }
    
    public void setAplicationContext(VaadinRequest vaadinRequest){
    	WrappedSession session = vaadinRequest.getWrappedSession();
    	HttpSession httpSession = ((WrappedHttpSession) session).getHttpSession();
    	ServletContext servletContext = httpSession.getServletContext();
    	applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }
    
    public ApplicationContext getAplicationContext(){
    	return applicationContext;
    }
    
}
