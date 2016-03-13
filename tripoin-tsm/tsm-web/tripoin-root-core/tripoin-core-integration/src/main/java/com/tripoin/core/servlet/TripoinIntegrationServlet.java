package com.tripoin.core.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.service.IGenericManagerJpa;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public class TripoinIntegrationServlet extends DispatcherServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2658977332610074003L;
	private static Logger LOGGER = LoggerFactory.getLogger(TripoinIntegrationServlet.class);
	
    private static final String CONTEXT_CONFIG_LOCATION_PARAMETER = "contextConfigLocation";
    
    /**
     * Spring Application Context
     */
    private transient ApplicationContext applicationContext;
    
    private LocaleResolver localeResolver;

	@Override
	public void init(ServletConfig config) throws ServletException {
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        if (config.getInitParameter(CONTEXT_CONFIG_LOCATION_PARAMETER) != null) {
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setParent(applicationContext);
            context.setConfigLocation(config.getInitParameter(CONTEXT_CONFIG_LOCATION_PARAMETER));
            context.setServletConfig(config);
            context.setServletContext(config.getServletContext());
            context.refresh();
            applicationContext = context;
        }
        if (SpringApplicationContext.getApplicationContext() == null){
            SpringApplicationContext.setApplicationContext(applicationContext);
        }
        super.init(config);  	
        initLocaleResolver(applicationContext); 
        startupInitialized(config.getServletContext());
	}
	
	private void initLocaleResolver(ApplicationContext context) {
        try {
            this.localeResolver = (LocaleResolver) context.getBean(DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME,
                    LocaleResolver.class);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Using LocaleResolver [" + this.localeResolver + "]");
            }
        } catch (NoSuchBeanDefinitionException ex) {
            this.localeResolver = new SessionLocaleResolver();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to locate LocaleResolver with name '"
                        + DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME + "' using default [" + localeResolver + "]");
            }
        }
    }
	
	public void startupInitialized(ServletContext servletContext) {
		LOGGER.debug("Initializing context...");
		IGenericManagerJpa dataLoadStarted = (IGenericManagerJpa) applicationContext.getBean("iGenericManagerJpa");
		try {
			List<MenuData> menuDatas = new ArrayList<MenuData>();
			Object[] param = new Object[] { RoleConstant.ROLE_ADMIN, ParameterConstant.VIEW_WEB, ParameterConstant.VIEW_WEB_MOBILE };
			List<Menu> menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_ADMIN_WEB, menuDatas);
			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_ADMIN, ParameterConstant.VIEW_MOBILE, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_ADMIN_MOBILE, menuDatas);
			
			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_SALESMAN, ParameterConstant.VIEW_WEB, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_SALESMAN_WEB, menuDatas);
			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_SALESMAN, ParameterConstant.VIEW_MOBILE, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_SALESMAN_MOBILE, menuDatas);

			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_AREASALESMANAGER, ParameterConstant.VIEW_WEB, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_AREASALESMANAGER_WEB, menuDatas);
			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_AREASALESMANAGER, ParameterConstant.VIEW_MOBILE, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_AREASALESMANAGER_MOBILE, menuDatas);

			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_NATIONALSALESMANAGER, ParameterConstant.VIEW_WEB, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_NATIONALSALESMANAGER_WEB, menuDatas);
			menuDatas = new ArrayList<MenuData>();
			param = new Object[] { RoleConstant.ROLE_NATIONALSALESMANAGER, ParameterConstant.VIEW_MOBILE, ParameterConstant.VIEW_WEB_MOBILE };
			menus = dataLoadStarted.loadObjectsJQLStatement("SELECT mn FROM Menu mn INNER JOIN mn.roles role WHERE role.code = ? AND (mn.viewType = ? OR mn.viewType = ?) ORDER BY mn.tree ASC", param, null);
			for (Menu menu : menus)
				menuDatas.add(new MenuData(menu));
			servletContext.setAttribute(ApplicationContextConstant.CONTEXT_MENU_ROLE_NATIONALSALESMANAGER_MOBILE, menuDatas);
		} catch (Exception e) {
			LOGGER.error("Error Initialized Web Service Integration", e);		
		}
	}
}
