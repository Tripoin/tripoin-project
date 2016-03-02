package com.tripoin.web.view.profile;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.web.TripoinUI;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.service.IUserService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "changePassword", cached = true)
public class ChangePasswordView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IStateFullRest stateFullRest;

    private String currentUsername;
    private String currentPassword;
    
    private final PasswordField oldPassword = new PasswordField("Current Password");
    private final PasswordField newPassword = new PasswordField("New Password");
    private final PasswordField reTypePassword = new PasswordField("Re-type New Password");
    private Notification notification = new Notification("");
    
    private boolean isFailure = true;
    
    @PostConstruct
    public void init() throws Exception {
        if(stateFullRest.getHeaders().get(EWebUIConstant.COOKIE.toString()) != null && !stateFullRest.getHeaders().get(EWebUIConstant.COOKIE.toString()).isEmpty()){
        	List<String> cookies = stateFullRest.getHeaders().get(EWebUIConstant.COOKIE.toString());
        	String basicAuthorization = "";
        	for(String cookie : cookies){
        		if(cookie.startsWith(EWebUIConstant.AUTHORIZATION.toString())){
        			basicAuthorization = cookie.replaceAll(EWebUIConstant.AUTHORIZATION.toString().concat("="), "").replaceAll(EWebUIConstant.REGEX_AUTHORIZATION.toString(), "");
        			break;
        		}
        	}        	
        	byte[] oauth = Base64.decode(basicAuthorization);
        	this.currentUsername = new String(oauth).split(":")[0];
        	this.currentPassword = new String(oauth).replaceFirst(currentUsername.concat(":"), "");
        }
        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        row.setMargin(false);
        row.setWidth("100%");
        addComponent(row);
        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Change Password");
        title.addStyleName("h1");
        formTitle.addComponent(title);        
        row.addComponent(formTitle);

        final FormLayout form = new FormLayout();
        form.setStyleName("tripoin-custom-form");
        form.setMargin(false);
        addComponent(form);
        
        Label section = new Label(" ");
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("80%");
        form.addComponent(section);       
        
        oldPassword.setRequired(true);
        oldPassword.setWidth("45%");
        oldPassword.addStyleName("small");
        form.addComponent(oldPassword);        
        
        newPassword.setRequired(true);
        newPassword.setWidth("45%");
        newPassword.addStyleName("small");
        form.addComponent(newPassword);        
        
        reTypePassword.setRequired(true);
        reTypePassword.setWidth("45%");
        reTypePassword.addStyleName("small");
        form.addComponent(reTypePassword);  

        Button save = new Button("Save", this);
        save.addStyleName("primary");
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(save);
        
    	Label messageAfterSave = new Label("*)  After pressing the Save button, system will be Logout.");
    	Panel warningPanel = new Panel("Warning");
        warningPanel.setStyleName("bold");
        warningPanel.setIcon(FontAwesome.WARNING);
        final VerticalLayout content = new VerticalLayout();
        warningPanel.setContent(content);
        content.setSpacing(true);
        content.setMargin(true);
        content.addComponent(messageAfterSave);
        addComponent(warningPanel);
		
		notification.setCaption("Error");
        notification.setDescription("New Password must be equals Re-type New Password!\n"
				+ "Length at least 6 characters and maximum of 20.\n"
				+ "No whitespace allowed in the entire string!\n"
				+ "Must contains one digit from 0-9!\n"
				+ "Must contains one lowercase or one uppercase characters!");
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }

	@Override
	public void buttonClick(ClickEvent event) {
		oldPassword.setComponentError(null);
		newPassword.setComponentError(null);
		reTypePassword.setComponentError(null);	
		if(oldPassword.getValue().equals(currentPassword)){
			if(newPassword.getValue() == null || "".equals(newPassword.getValue()) || newPassword.getValue().isEmpty())
				newPassword.setComponentError(new UserError("New Password not null!"));
			else if(reTypePassword.getValue() == null || "".equals(reTypePassword.getValue()) || reTypePassword.getValue().isEmpty())
				reTypePassword.setComponentError(new UserError("Re-type New Password not null!"));				
			else{
				if(newPassword.getValue().equals(reTypePassword.getValue())){
					if(!newPassword.getValue().matches(EWebUIConstant.REGEX_CONTAINS_MINMAX.toString())){
						newPassword.setComponentError(new UserError("Length at least 6 characters and maximum of 20."));
						reTypePassword.setComponentError(new UserError("Length at least 6 characters and maximum of 20."));						
					}else if(!newPassword.getValue().matches(EWebUIConstant.REGEX_CONTAINS_WHITESPACE.toString())){
						newPassword.setComponentError(new UserError("No whitespace allowed in the entire string!"));
						reTypePassword.setComponentError(new UserError("No whitespace allowed in the entire string!"));						
					}else if(!newPassword.getValue().matches(EWebUIConstant.REGEX_CONTAINS_DIGIT.toString())){
						newPassword.setComponentError(new UserError("Must contains one digit from 0-9!"));
						reTypePassword.setComponentError(new UserError("Must contains one digit from 0-9!"));						
					}else if(!newPassword.getValue().matches(EWebUIConstant.REGEX_CONTAINS_LOWERUPERCASE.toString())){
						newPassword.setComponentError(new UserError("Must contains one lowercase or one uppercase characters!"));
						reTypePassword.setComponentError(new UserError("Must contains one lowercase or one uppercase characters!"));						
					}else{
						isFailure = false;
						byte[] oauth = (ParameterConstant.TRIPOIN_AUTHORIZATION.concat(this.currentUsername.concat(":").concat(newPassword.getValue()))).getBytes();
						String basicAuthorization = Base64.encode(oauth);
						GeneralTransferObject generalTransferObject = userService.updateUser(basicAuthorization);
						if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
							stateFullRest.clearAllCookies();
							stateFullRest.setUsername(this.currentUsername);
							stateFullRest.setPassword(newPassword.getValue());
							TripoinUI.get().close();	
						}else{
							notification.setDescription(generalTransferObject.getResponseMsg());
							isFailure = true;
						}
					}
				}else{
					newPassword.setComponentError(new UserError("New Password must be equals Re-type New Password!"));
					reTypePassword.setComponentError(new UserError("New Password must be equals Re-type New Password!"));						
				}
			}
		}else
			oldPassword.setComponentError(new UserError("Current password not valid."));
		
		if(isFailure)
	        notification.show(Page.getCurrent());
	}
    
}
