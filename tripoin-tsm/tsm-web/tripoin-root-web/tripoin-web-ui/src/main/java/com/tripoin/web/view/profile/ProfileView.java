package com.tripoin.web.view.profile;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.util.time.TimeAgo;
import com.tripoin.util.ui.platform.IdentifierPlatform;
import com.tripoin.web.TripoinUI;
import com.tripoin.web.common.EWebUIConstant;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IProfileService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Upload.StartedEvent;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "profile", cached = false)
public class ProfileView extends VerticalLayout implements View, ClickListener, Button.ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;

    @Autowired
    private IProfileService profileService;
    
    @Autowired
    private ICommonRest commonRest;
    
    private ProfileData profileData;
    
    private final FormLayout form = new FormLayout();
    private Embedded profilePhoto = new Embedded();
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
    private ProfileImageUploader receiver = new ProfileImageUploader();
    private Upload upload = new Upload(null, receiver);
    private ExternalResource urlImageProfileResource;
    private String urlResourcesImage;
    private Notification notification = new Notification("");
    
    @PostConstruct
    public void init() throws Exception {
		profileData = profileService.getProfile();
		
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        row.setMargin(false);
        row.setWidth("100%"); 
        addComponent(row);        
        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Account Settings");
        title.addStyleName("h1");
        formTitle.addComponent(title);
        row.addComponent(formTitle);  
        
        VerticalLayout uploadLayout = new VerticalLayout();
        uploadLayout.setMargin(false);
        uploadLayout.setWidth("40%");        
        urlResourcesImage = commonRest.getUrl(WebServiceConstant.HTTP_RESOURCES_IMAGES.concat("/"));
        String urlImage = urlResourcesImage.concat("profile-default-300px.png"); 
        if(profileData.getPhoto() != null)
        	urlImage = urlResourcesImage.concat(profileData.getResourcesUUID()).concat("/").concat(profileData.getPhoto());
        urlImageProfileResource = new ExternalResource(urlImage);        	
        profilePhoto.setSource(urlImageProfileResource); 
        profilePhoto.setWidth("100%");
        upload.setWidth("100%");
        upload.setImmediate(true);
        upload.addStartedListener(new StartedListener() {			
			private static final long serialVersionUID = 1784510326592657108L;
			@Override
			public void uploadStarted(StartedEvent event) {
				String typeFile = event.getMIMEType().split("/")[0];
	        	if(!EWebUIConstant.TYPE_FILE_IMAGE.toString().equals(typeFile)){
	        		upload.interruptUpload();
	        	}
			}
		});
        upload.addFinishedListener(new FinishedListener() {
			private static final long serialVersionUID = -9184461198940643739L;
			@Override
			public void uploadFinished(FinishedEvent event) {       
				String urlImage = urlResourcesImage.concat("profile-default-300px.png");
				String fileName = receiver.getFile().getName();
	        	IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
				Map<String, Object> data = new HashMap<String, Object>();
				data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE.concat("CREATED-BY"), username.getValue());
				data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE.concat("CREATED-IP"), identifierPlatform.getIPAddress());
				data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE.concat("CREATED-PLATFORM"), identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
				GeneralTransferObject generalTransferObject = profileService.updatePhotoProfile(receiver.getFile(), data);
		        if(ParameterConstant.RESPONSE_SUCCESS.equals(generalTransferObject.getResponseMsg()))
		        	urlImage = urlResourcesImage.concat(profileData.getResourcesUUID()).concat("/").concat(fileName);
		        urlImageProfileResource = new ExternalResource(urlImage); 
		        profilePhoto.setSource(urlImageProfileResource);
				TripoinUI.get().updateImageProfile(urlImage);
			}
		});
        upload.addSucceededListener(new SucceededListener() {
			private static final long serialVersionUID = -7061365876392881684L;
			@Override
			public void uploadSucceeded(SucceededEvent event) {
				receiver.getFile().delete();
			}
		});
        uploadLayout.addComponent(profilePhoto);
        uploadLayout.addComponent(upload);
        row.addComponent(uploadLayout);
        row.setComponentAlignment(uploadLayout, Alignment.TOP_RIGHT);  

        form.setMargin(false);
        form.addStyleName("light");
        addComponent(form);

        Label section = new Label("Personal Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        form.addComponent(section); 
        
        username.setValue(profileData.getUserData().getUsername());
        username.setWidth("50%");
        username.setRequired(true);
        form.addComponent(username);       
        
        name.setValue(profileData.getName());
        name.setWidth("50%");
        name.setRequired(true);
        form.addComponent(name);

        HorizontalLayout placeDateOfBirth = new HorizontalLayout();
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
        
        phone.setWidth("50%");
        phone.setValue(profileData.getPhone());
        phone.setRequired(true);
        form.addComponent(phone);
        
        telp.setWidth("50%");
        telp.setValue(profileData.getTelp());
        form.addComponent(telp);
        
        email.setValue(profileData.getEmail());
        email.setWidth("50%");
        email.setRequired(true);
        form.addComponent(email);
        
        address.setValue(profileData.getAddress());
        address.setWidth("50%");
        address.setRequired(true);
        form.addComponent(address);

        section = new Label("Additional Info");
        section.addStyleName("h3");
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
		
		notification.setCaption("Error");
		notification.setStyleName("system closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

	@Override
	public void click(ClickEvent event) {
		
	}

	@Override
	public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
        boolean readOnly = form.isReadOnly();
        if (readOnly) {
            form.setReadOnly(false);
            name.setReadOnly(false);
            name.setComponentError(null);
            birthPlace.setReadOnly(false);
            birthPlace.setComponentError(null);
            birthDate.setReadOnly(false);
            birthDate.setComponentError(null);
            gender.setReadOnly(false);
            address.setReadOnly(false);
            address.setComponentError(null);
            email.setReadOnly(false);
            email.setComponentError(null);
            phone.setReadOnly(false);
            phone.setComponentError(null);
            telp.setReadOnly(false);
            telp.setComponentError(null);
            bio.setReadOnly(false);
            bio.setComponentError(null);
            form.removeStyleName("light");
            form.addStyleName("tripoin-custom-form");
            event.getButton().setCaption("Save");
            event.getButton().addStyleName("primary");
        } else {
        	IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
        	String allNotif = "";
            boolean isValid = true;
        	if(name.getValue()==null || name.getValue().isEmpty()){
        		name.setComponentError(new UserError("Name not null"));
        		allNotif=allNotif.concat("Name not null!\n");
        		isValid=false;
        	}
        	if(birthPlace.getValue()==null || birthPlace.getValue().isEmpty()){
        		birthPlace.setComponentError(new UserError("Birth place not null"));
        		allNotif=allNotif.concat("Birth place not null!\n");
        		isValid=false;
        	}
        	if(!birthDate.isValid() || birthDate.getValue()==null){
        		birthDate.setComponentError(new UserError("Birth date incorrectly"));
        		allNotif=allNotif.concat("Birth date incorrectly!\n");
        		isValid=false;
        	}
        	if(phone.getValue()==null || phone.getValue().isEmpty()){
        		phone.setComponentError(new UserError("Phone not null"));
        		allNotif=allNotif.concat("Phone not null!\n");
        		isValid=false;
        	}
        	if(telp.getValue()==null || telp.getValue().isEmpty()){
        		telp.setComponentError(new UserError("Telp not null"));
        		allNotif=allNotif.concat("Telp not null!\n");
        		isValid=false;
        	}
        	if(email.getValue()==null || email.getValue().isEmpty()){
        		email.setComponentError(new UserError("Email not null"));
        		allNotif=allNotif.concat("Email not null!\n");
        		isValid=false;
        	}else if (!email.getValue().matches(EWebUIConstant.REGEX_EMAIL.toString())) {
        		email.setComponentError(new UserError("Email format not valid"));
        		allNotif=allNotif.concat("Email format not valid!\n");
        		isValid=false;
			}
        	if(address.getValue()==null || address.getValue().isEmpty()){
        		address.setComponentError(new UserError("Address not null"));
        		allNotif=allNotif.concat("Address not null!\n");
        		isValid=false;
        	}	
        	if(isValid){
            	profileData.setName(name.getValue());
            	profileData.setBirthplace(birthPlace.getValue());
            	profileData.setBirthdate(ParameterConstant.FORMAT_DEFAULT.format(birthDate.getValue()));
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

                name.setComponentError(null);
                birthPlace.setComponentError(null);
                birthDate.setComponentError(null);
                address.setComponentError(null);
                email.setComponentError(null);
                phone.setComponentError(null);
                telp.setComponentError(null);
                bio.setComponentError(null);
                form.setReadOnly(true);
                name.setReadOnly(true);
                birthPlace.setReadOnly(true);
                birthDate.setReadOnly(true);
                gender.setReadOnly(true);
                address.setReadOnly(true);
                email.setReadOnly(true);
                phone.setReadOnly(true);
                telp.setReadOnly(true);
                bio.setReadOnly(true);
                lastModified.setValue(statusModified(profileData.getModifiedTime()));
                form.addStyleName("light");
                form.removeStyleName("tripoin-custom-form");
                event.getButton().setCaption("Edit");
                event.getButton().removeStyleName("primary");
        	} else{
        		notification.setDescription(allNotif);
    	        notification.show(Page.getCurrent());				
			}
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
