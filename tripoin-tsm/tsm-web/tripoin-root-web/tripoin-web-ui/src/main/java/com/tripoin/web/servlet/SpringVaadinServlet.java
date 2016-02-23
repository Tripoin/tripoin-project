package com.tripoin.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IDataLoadStarted;
import com.tripoin.web.service.impl.DataLoadStartedImpl;
import com.tripoin.web.servlet.view.ValoThemeSessionInitListener;
import com.vaadin.server.Constants;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SpringVaadinServlet extends VaadinServlet implements SessionInitListener, SessionDestroyListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5912117157703160443L;
	private static Logger LOGGER = LoggerFactory.getLogger(SpringVaadinServlet.class);
	
    /**
     * Servlet parameter name for system message bean
     */
    private static final String SYSTEM_MESSAGES_BEAN_NAME_PARAMETER = "systemMessagesBeanName";
    private static final String CONTEXT_CONFIG_LOCATION_PARAMETER = "contextConfigLocation";
    
    /**
     * Spring Application Context
     */
    private transient ApplicationContext applicationContext;
    
    /**
     * system message bean name
     */
    private String systemMessagesBeanName = "";
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
        if (config.getInitParameter(SYSTEM_MESSAGES_BEAN_NAME_PARAMETER) != null) {
            systemMessagesBeanName = config.getInitParameter(SYSTEM_MESSAGES_BEAN_NAME_PARAMETER);
        }
        if (SpringApplicationContext.getApplicationContext() == null){
            SpringApplicationContext.setApplicationContext(applicationContext);
        }
        super.init(config);  	
        initLocaleResolver(applicationContext); 
        startupInitialized(config.getServletContext());
    }

    protected void initializePlugin(VaadinServletService service) {
        // Spring system messages provider
        if (systemMessagesBeanName != null && systemMessagesBeanName != "") {
            SpringVaadinSystemMessagesProvider messagesProvider = new SpringVaadinSystemMessagesProvider(applicationContext, systemMessagesBeanName);
            service.setSystemMessagesProvider(messagesProvider);
        }
        String uiProviderProperty = service.getDeploymentConfiguration().getApplicationOrSystemProperty(Constants.SERVLET_PARAMETER_UI_PROVIDER, null);
        // Add SpringUIProvider if custom provider doesn't defined.
        if (uiProviderProperty == null) {
            service.addSessionInitListener(new SessionInitListener() {
            	
				private static final long serialVersionUID = 7821444940054741472L;

				@Override
                public void sessionInit(SessionInitEvent event) throws ServiceException {
                    event.getSession().addUIProvider(new SpringUIProvider());
                }
            });
        }
    }

    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) throws ServiceException {
        final VaadinServletService service = super.createServletService(deploymentConfiguration);
        initializePlugin(service);
        return service;
    }
    
    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().addSessionInitListener(new ValoThemeSessionInitListener());
        getService().addSessionDestroyListener(this);
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
		final Locale locale = localeResolver.resolveLocale(request);
        LocaleContextHolder.setLocale(locale);
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(requestAttributes);
        try {
            super.service(new HttpServletRequestWrapper(request) {

                @Override
                public Locale getLocale() {
                    return locale;
                }
            }, response);
        } finally {
            if (!locale.equals(LocaleContextHolder.getLocale())) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("locale changed, updating locale resolver");
                }
                localeResolver.setLocale(request, response, LocaleContextHolder.getLocale());
            }
            LocaleContextHolder.resetLocaleContext();
            RequestContextHolder.resetRequestAttributes();
        }
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

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	public void startupInitialized(ServletContext servletContext) {
		LOGGER.debug("Initializing context...");
		IDataLoadStarted dataLoadStarted = applicationContext.getBean(DataLoadStartedImpl.class);
		try {
			List<OccupationData> occupationDatas = dataLoadStarted.loadOccupationData();
			servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_OCCUPATION, occupationDatas);
			List<EmployeeData> employeeNotSalesmanList = dataLoadStarted.loadEmployeeAreaSalesManagerData();
			servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_AREASALESMANAGER, employeeNotSalesmanList);
			employeeNotSalesmanList = dataLoadStarted.loadEmployeeNationalSalesManagerData();
			servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_EMPLOYEE_NATIONALSALESMANAGER, employeeNotSalesmanList);
			List<AreaData> areaDatas = dataLoadStarted.loadAreaData();
			servletContext.setAttribute(WebServiceConstant.CONTEXT_CONSTANT_AREA, areaDatas);
		} catch (Exception e) {
			if(HttpStatus.NOT_FOUND.equals(dataLoadStarted.getStatusCode())){
				LOGGER.warn("Response : ".concat(dataLoadStarted.getStatusCode().value()+" ").concat(dataLoadStarted.getStatusCode().getReasonPhrase()));
				LOGGER.warn("Please check Web Service, and restart this Web Container");
			}else LOGGER.error("Response : ".concat(dataLoadStarted.getStatusCode().value()+" ").concat(dataLoadStarted.getStatusCode().getReasonPhrase()), e);			
		}
	}
    
}
