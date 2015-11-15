package com.tripoin.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.MenuData;
import com.tripoin.web.authentication.IAccessControl;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IForgotPasswordService;
import com.tripoin.web.service.ILogoutService;
import com.tripoin.web.servlet.DiscoveryNavigator;
import com.tripoin.web.view.ErrorView;
import com.tripoin.web.view.home.HomeView;
import com.tripoin.web.view.login.LoginScreen;
import com.tripoin.web.view.login.LoginScreen.LoginListener;
import com.tripoin.web.view.menu.BaseMenuLayout;
import com.tripoin.web.view.menu.RootMenuLayout;
import com.tripoin.web.view.menu.BaseMenuLayout.LogoutListener;
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
import com.vaadin.server.VaadinSession;
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
    private CssLayout menuItems;
    private CssLayout menuItemsLayout;
	private RootMenuLayout root = new RootMenuLayout();
    private ComponentContainer viewDisplay = root.getContentContainer();
    private DiscoveryNavigator navigator;
    private ApplicationContext applicationContext;
    
    @Autowired
    private LoginScreen loginScreen;
	
	@Autowired
    private IAccessControl accessControl;
	
	@Autowired
	private IStateFullRest stateFullRest;

	@Autowired
	private BaseMenuLayout baseMenuLayout;
	
	@Autowired
	private ILogoutService logoutService;
	
	@Autowired
	private IForgotPasswordService forgotPasswordService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try{
        	if(WebServiceConstant.HTTP_FORGOT_PASSWORD_PATH.equals(getPage().getLocation().getPath())){
        		String[] rawParameter = getPage().getLocation().getQuery().split("&");
        		String username =rawParameter[0].split("=")[1];
        		String uuid =rawParameter[1].split("=")[1];
        		GeneralTransferObject generalTransferObject = forgotPasswordService.verifyForgotPassword(username, uuid);
    			Notification notificationVerifyEmail = new Notification("");  
        		notificationVerifyEmail.setStyleName("system closable");
        		notificationVerifyEmail.setPosition(Position.BOTTOM_CENTER);
                notificationVerifyEmail.setDelayMsec(7500);
        		if("0".equals(generalTransferObject.getResponseCode())){			
        			notificationVerifyEmail.setCaption(EWebUIConstant.NOTIF_SUCCESS_FORGOT_PASSWORD_TITLE.toString());
        			notificationVerifyEmail.setDescription(EWebUIConstant.NOTIF_SUCCESS_FORGOT_PASSWORD_DESC.toString());
        			notificationVerifyEmail.show(Page.getCurrent());        			
        		}else if("2".equals(generalTransferObject.getResponseCode())){			
        			notificationVerifyEmail.setCaption(EWebUIConstant.NOTIF_EMAIL_FAILURE_VERIFY_FORGOT_PASSWORD_TITLE.toString());
        			notificationVerifyEmail.setDescription(EWebUIConstant.NOTIF_LINK_EXPIRED_FORGOT_PASSWORD_DESC.toString());
        			notificationVerifyEmail.show(Page.getCurrent());          			
        		}else{			
        			notificationVerifyEmail.setCaption(EWebUIConstant.NOTIF_EMAIL_FAILURE_VERIFY_FORGOT_PASSWORD_TITLE.toString());
        			notificationVerifyEmail.setDescription(EWebUIConstant.NOTIF_LINK_NULL_FORGOT_PASSWORD_DESC.toString());
        			notificationVerifyEmail.show(Page.getCurrent());            			
        		}
        	}
        	getSession().setErrorHandler(this);
	        Responsive.makeResponsive(this);
	        setLocale(Locale.US);
	        
	        if (accessControl.isUserSignedIn()){
	            mainView();
	        }else{
	        	loginScreen.addLoginListener(new LoginListener() {
					private static final long serialVersionUID = 5327649431527930757L;
					@Override
					public void loginSuccessful() {
						mainView();
					}
				});
	        	getPage().setTitle("Tripoin Login");
	            setContent(loginScreen);
	        }       	
        }catch(Exception e){
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
        	error(new com.vaadin.server.ErrorEvent(new AccessDeniedException(errors.toString())));
        }
    }

    protected void mainView() {
    	getPage().setTitle("Tripoin Web Application");
    	setTheme("tripoin-valo");
    	baseMenuLayout.addLogoutListener(new LogoutListener() {
			private static final long serialVersionUID = -8249206203052668842L;
			@Override
			public void doLogout() {
				close();
			}
		});
    	if(stateFullRest != null && stateFullRest.getMenuDatas() != null && !stateFullRest.getMenuDatas().isEmpty())
    		baseMenuLayout.setMenuDatas(stateFullRest.getMenuDatas());
        if(menuItems == null)
        	menuItems = baseMenuLayout.getMenu();
    	if(menuItemsLayout == null)
    		menuItemsLayout = baseMenuLayout.getMenuItemsLayout();
    	baseMenuLayout.updateUser(accessControl.getUsername());
        if (getPage().getWebBrowser().isIE() && getPage().getWebBrowser().getBrowserMajorVersion() == 9) {
        	baseMenuLayout.setWidth("320px");
        }
        root.addMenu(menuItems);
        root.setWidth("100%"); 
        addStyleName(ValoTheme.UI_WITH_MENU);
		removeStyleName("login-screen");
		removeStyleName("login-information");
		removeStyleName("login-form");
		removeStyleName("centering-layout");
        setContent(root);
        generateNavigator();
    }
    
    private void generateNavigator(){
        navigator = new DiscoveryNavigator(this, viewDisplay);
        final String f = Page.getCurrent().getUriFragment();
        if (f == null || f.isEmpty() || EWebUIConstant.HOME_VIEW.toString().equals(f) || HomeView.VIEW_NAME.equals(f) || EWebUIConstant.NAVIGATE_NULL.toString().equals(f)) 
            navigator.navigateTo(HomeView.VIEW_NAME);
        else if(f.startsWith("!") && baseMenuLayout.getMapDataMenu().containsKey(f.substring(1)))
        	navigator.navigateTo(f);
        else{
        	UI.getCurrent().getPage().setUriFragment(null, true);
        	navigator.navigateTo(HomeView.VIEW_NAME);
        }
        navigator.setErrorView(ErrorView.class);
        navigator.addViewChangeListener(new ViewChangeListener() {
			private static final long serialVersionUID = -1255484519903571054L;
			@Override
            public boolean beforeViewChange(final ViewChangeEvent event) {
                return true;
            }
            @Override
            public void afterViewChange(final ViewChangeEvent event) {
                for (final Iterator<com.vaadin.ui.Component> it = menuItemsLayout.iterator(); it.hasNext();) {
                    it.next().removeStyleName("selected");
                }
                for (MenuData menuData : stateFullRest.getMenuDatas()) {
                    if (event.getViewName().equals(menuData.getCode())) {
                        for (final Iterator<com.vaadin.ui.Component> it = menuItemsLayout.iterator(); it.hasNext();) {
                            final com.vaadin.ui.Component c = it.next();
                            if (c.getCaption() != null && c.getCaption().startsWith(menuData.getName())) {
                                c.addStyleName("selected");
                                break;
                            }
                        }
                        break;
                    }
                }
                baseMenuLayout.removeStyleName("valo-menu-visible");
            }
        });    	
    }

    public static TripoinUI get() {
        return (TripoinUI) UI.getCurrent();
    }    

	@Override
	public void close() {
		logoutService.doLogout();
		SecurityContextHolder.clearContext();
		if(stateFullRest != null && stateFullRest.getAdditionalDataMenu() != null && !stateFullRest.getAdditionalDataMenu().isEmpty())
			stateFullRest.clearAllCookies();
        VaadinSession.getCurrent().close();
		getSession().close();
		getSession().getSession().invalidate();
		UI.getCurrent().getPage().setLocation("j_spring_security_logout");
	}

	@Override
	public void error(com.vaadin.server.ErrorEvent event) {
		String description = "The server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).";
		AccessDeniedException accessDeniedException = new AccessDeniedException(description);
		ErrorView errorView;
        if("false".equals(VaadinServlet.getCurrent().getServletContext().getInitParameter("productionMode"))){
    		if (event.getThrowable().getCause() instanceof AccessDeniedException)
                accessDeniedException = (AccessDeniedException) event.getThrowable().getCause();
    		if (event.getThrowable() instanceof AccessDeniedException)
                accessDeniedException = (AccessDeniedException) event.getThrowable();        	
        }
		Notification notification = new Notification("");
		notification.setCaption("Bad Request");
        notification.setDescription(description);
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(7500);
        if(Page.getCurrent() == null)
        	notification.show(getPage());
        else
        	notification.show(Page.getCurrent());
        errorView = new ErrorView();
        errorView.setDescription(accessDeniedException.getMessage());
        setContent(errorView);
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