package com.tripoin.web.view.menu;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.MenuData;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.util.ui.icon.BaseIcon;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IProfileService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class BaseMenuLayout extends CssLayout implements View {

	private static final long serialVersionUID = -4795419684894048255L;
    private final BaseIcon baseIcon = new BaseIcon(100);
    private MenuItem settingsItem;
    private CssLayout menuItemsLayout = new CssLayout();
    private LogoutListener logoutListener;
    private List<MenuData> menuDatas;
    private Map<String, String> mapDataMenu = new LinkedHashMap<String, String>();
    private Component titleComponent;
    private Component userMenuComponent;
    private Component toggleButtonComponent;
    private Component menuItemsComponent;
    private ExternalResource urlImageProfileResource;
    private String urlResourcesImage;
    private ProfileData profileData;
    
    @Autowired
    private IProfileService profileService;
    
    @Autowired
    private ICommonRest commonRest;
    
	public CssLayout getMenuItemsLayout() {
		return menuItemsLayout;
	}
	
	public void setMenuDatas(List<MenuData> menuDatas) {
		this.menuDatas = menuDatas;
	}

	public void addLogoutListener(LogoutListener logoutListener){
		this.logoutListener = logoutListener;
	}

	public Map<String, String> getMapDataMenu() {
		return mapDataMenu;
	}

	public void setMapDataMenu(Map<String, String> mapDataMenu) {
		this.mapDataMenu = mapDataMenu;
	}

	public void setUrlImageProfileResource(ExternalResource urlImageProfileResource) {
		this.urlImageProfileResource = urlImageProfileResource;
	}

	@PostConstruct
	public void init(){
		if(mapDataMenu.isEmpty()){
			mapDataMenu.put("profile", "Account Setings");
			mapDataMenu.put("changePassword", "Change Password");
		}
        setId("BaseMenu");
	}

	public BaseMenuLayout getMenu() {
		profileData = profileService.getProfile();
		titleComponent = buildTitle();
		userMenuComponent = buildUserMenu();
		toggleButtonComponent = buildToggleButton();
		menuItemsComponent = buildMenuItems();
        addComponent(titleComponent, 0);        
        addComponent(userMenuComponent, 1);        
        addComponent(toggleButtonComponent, 2);
        addComponent(menuItemsComponent, 3);
        return this;
	}
	
	private Component buildTitle() {
        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName("valo-menu-title");
        final Label title = new Label("<h3>Web Application <strong>Tripoin</strong></h3>", ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);
		return top;
	}
	
	private Component buildUserMenu() {
        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");      
        urlResourcesImage = commonRest.getUrl(WebServiceConstant.HTTP_RESOURCES_IMAGES.concat("/"));
        String urlImage = urlResourcesImage.concat("profile-default-300px.png");
        if(profileData.getPhoto() != null)
           	urlImage = urlResourcesImage.concat(profileData.getResourcesUUID()).concat("/").concat(profileData.getPhoto());
        urlImageProfileResource = new ExternalResource(urlImage);
        settingsItem = settings.addItem("", urlImageProfileResource, null);
        settingsItem.addItem("Account Settings", new Command() {
			private static final long serialVersionUID = 8813252433421821224L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				UI.getCurrent().getNavigator().navigateTo("profile");
			}
		});
        settingsItem.addItem("Change Password", new Command() {
			private static final long serialVersionUID = 8813252433421821224L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				UI.getCurrent().getNavigator().navigateTo("changePassword");
			}
		});
        settingsItem.addSeparator();
        settingsItem.addItem("Log Out", FontAwesome.SIGN_OUT, new Command() {			
			private static final long serialVersionUID = -7829505006330125630L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				logoutListener.doLogout();
			}
		});
		return settings;
	}
	
	private Component buildToggleButton() {
		final Button showMenu = new Button("Menu", new ClickListener() {        	
			private static final long serialVersionUID = -4671912497297145261L;
			@Override
            public void buttonClick(final ClickEvent event) {
                if (getStyleName().contains("valo-menu-visible")) {
                    removeStyleName("valo-menu-visible");
                } else {
                    addStyleName("valo-menu-visible");
                }
            }
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
		return showMenu;
	}
	
	private Component buildMenuItems() {
		Label label = null;
		for(final MenuData menuData : menuDatas){
			if(menuData.getLevel() == 1){
				 label = new Label(menuData.getName(), ContentMode.HTML);
	             label.setPrimaryStyleName("valo-menu-subtitle");
	             label.addStyleName("h4");
	             label.setSizeUndefined();
	             menuItemsLayout.addComponent(label);
			}
			if(ParameterConstant.MENU_PAGE.equals(menuData.getFunction())){
				mapDataMenu.put(menuData.getCode(), menuData.getName());
				final Button b = new Button(menuData.getName(), new ClickListener() {            	
					private static final long serialVersionUID = -8962701523482133238L;
					@Override
	                public void buttonClick(final ClickEvent event) {
						UI.getCurrent().getNavigator().navigateTo(menuData.getCode());
	                }
	            });            
	            b.setHtmlContentAllowed(true);
	            b.setPrimaryStyleName("valo-menu-item");
	            b.setIcon(baseIcon.get());
	            menuItemsLayout.addComponent(b);				
			}
		}
        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        return menuItemsLayout;
	}
	
	public void updateUser(String username){
		settingsItem.setText(username);
	}
	
    public interface LogoutListener extends Serializable {
        void doLogout();
    }

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
