package com.tripoin.web.view.profile;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.util.time.TimeAgo;
import com.tripoin.util.ui.platform.IdentifierPlatform;
import com.tripoin.web.service.IProfileService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "profile", cached = false)
public class ProfileView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;

    @Autowired
    private IProfileService profileService;
    
    private ProfileData profileData;
    
    private final FormLayout form = new FormLayout();
    private Image profilePhoto = new Image();
    private final TextField name = new TextField("Name");
    private final TextField username = new TextField("Username");
    private final TextField birthPlace = new TextField();
    private final DateField birthDate = new DateField();
    private final OptionGroup gender = new OptionGroup("Gender");
    private final TextField phone = new TextField("Mobile Phone");
    private final TextField telp = new TextField("Home Phone");
    private final TextField email = new TextField("Email");
    private final TextArea address = new TextArea("Address");
    private final RichTextArea bio = new RichTextArea("Bio");
    private Button edit;
    private Label lastModified;
    
    @PostConstruct
    public void init() throws Exception {
		profileData = profileService.getProfile();
        setMargin(true);

        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        
        Label title = new Label("Account Settings");
        title.addStyleName("h1");
        formTitle.addComponent(title);

        HorizontalLayout row = new HorizontalLayout();
        row.setSpacing(true);
        row.setMargin(false);
        row.setWidth("800px");
        row.addComponent(formTitle);
        addComponent(row);
                
        profilePhoto.setSource(new File(profileData.getPhoto()).exists() ? new FileResource(new File(profileData.getPhoto())) : new ThemeResource("../tripoin-valo/img/profile-pic-new-300px.png")); 
        profilePhoto.setWidth("150px");
        profilePhoto.setHeight("150px");
        profilePhoto.addStyleName("light2");
        row.addComponent(profilePhoto);
        row.setComponentAlignment(profilePhoto, Alignment.TOP_RIGHT);
        
        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName("light");
        addComponent(form);

        Label section = new Label("Personal Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        form.addComponent(section);        
        
        name.setValue(profileData.getName());
        name.setWidth("65%");
        name.setRequired(true);
        form.addComponent(name);
        
        username.setValue(profileData.getUserData().getUsername());
        username.setWidth("65%");
        username.setRequired(true);
        form.addComponent(username);

        HorizontalLayout placeDateOfBirth = new HorizontalLayout();
        placeDateOfBirth.setSpacing(true);
        placeDateOfBirth.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        placeDateOfBirth.setCaption("Place, Date of Birth");        
        birthPlace.setValue(profileData.getBirthplace());
        birthPlace.setWidth("45%");       
        try {
			birthDate.setValue(ParameterConstant.FORMAT_DEFAULT.parse(profileData.getBirthdate()));
		} catch (ReadOnlyException e) {
			e.printStackTrace();
		} catch (ConversionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        birthPlace.setRequired(true);
        birthDate.setRequired(true);
        placeDateOfBirth.addComponent(birthPlace);
        placeDateOfBirth.addComponent(birthDate);
        form.addComponent(placeDateOfBirth);
        
        gender.addItem(ParameterConstant.FEMALE);
        gender.addItem(ParameterConstant.MALE);
        if(ParameterConstant.MALE.equalsIgnoreCase(profileData.getGender()))
        	gender.select(ParameterConstant.MALE);
        else
        	gender.select(ParameterConstant.FEMALE);
        gender.addStyleName("horizontal");
        gender.setRequired(true);
        form.addComponent(gender);

        section = new Label("Contact Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        form.addComponent(section);
        
        phone.setWidth("65%");
        phone.setValue(profileData.getPhone());
        phone.setRequired(true);
        form.addComponent(phone);
        
        telp.setWidth("65%");
        telp.setValue(profileData.getTelp());
        form.addComponent(telp);
        
        email.setValue(profileData.getEmail());
        email.setWidth("65%");
        email.setRequired(true);
        form.addComponent(email);
        
        address.setValue(profileData.getAddress());
        address.setWidth("65%");
        address.setRequired(true);
        form.addComponent(address);

        section = new Label("Additional Info");
        section.addStyleName("h4");
        section.addStyleName("colored");
        form.addComponent(section);
        
        bio.setValue(profileData.getBio());
        bio.setWidth("100%");
        form.addComponent(bio);

        form.setReadOnly(true);
        name.setReadOnly(true);
        birthPlace.setReadOnly(true);
        birthDate.setReadOnly(true);
        username.setReadOnly(true);
        gender.setReadOnly(true);
        address.setReadOnly(true);
        email.setReadOnly(true);
        phone.setReadOnly(true);
        telp.setReadOnly(true);
        bio.setReadOnly(true);

        edit = new Button("Edit", this);
        edit.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(edit);

        if(profileData.getModifiedTime() != null){
        	lastModified = new Label(statusModified(profileData.getModifiedTime()));
        	lastModified.addStyleName("light");	
        	footer.addComponent(lastModified);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

	@Override
	public void buttonClick(ClickEvent event) {
        boolean readOnly = form.isReadOnly();
        if (readOnly) {
            form.setReadOnly(false);
            name.setReadOnly(false);
            birthPlace.setReadOnly(false);
            birthDate.setReadOnly(false);
            username.setReadOnly(false);
            gender.setReadOnly(false);
            address.setReadOnly(false);
            email.setReadOnly(false);
            phone.setReadOnly(false);
            telp.setReadOnly(false);
            bio.setReadOnly(false);            
            form.removeStyleName("light");
            event.getButton().setCaption("Save");
            event.getButton().addStyleName("primary");
        } else {
        	IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
        	profileData.setName(name.getValue());
        	profileData.setBirthplace(birthPlace.getValue());
        	profileData.setBirthdate(ParameterConstant.FORMAT_DEFAULT.format(birthDate.getValue()));
        	profileData.getUserData().setUsername(username.getValue());
        	profileData.setGender(gender.getValue().toString().toUpperCase());
        	profileData.setAddress(address.getValue());
        	profileData.setPhone(phone.getValue());
        	profileData.setTelp(telp.getValue());
        	profileData.setEmail(email.getValue());
        	profileData.setBio(bio.getValue());
        	profileData.setModifiedBy(username.getValue());
        	profileData.setModifiedIP(identifierPlatform.getIPAddress());
        	profileData.setModifiedTime(ParameterConstant.FORMAT_DEFAULT.format(new Date()));
        	profileData.setModifiedPlatform(identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));        	
        	profileService.updateProfile(profileData);
        	
            form.setReadOnly(true);
            name.setReadOnly(true);
            birthPlace.setReadOnly(true);
            birthDate.setReadOnly(true);
            username.setReadOnly(true);
            gender.setReadOnly(true);
            address.setReadOnly(true);
            email.setReadOnly(true);
            phone.setReadOnly(true);
            telp.setReadOnly(true);
            bio.setReadOnly(true);
            lastModified.setValue(statusModified(profileData.getModifiedTime()));
            form.addStyleName("light");
            event.getButton().setCaption("Edit");
            event.getButton().removeStyleName("primary");
        }
	}
	
	private String statusModified(String modifiedTime){
    	try {
			modifiedTime = TimeAgo.estimatedTime(ParameterConstant.FORMAT_DEFAULT.parse(modifiedTime), new Date());
		} catch (ParseException e) {
			modifiedTime = "";				
			e.printStackTrace();
		}	
    	return modifiedTime;
	}
    
}
