package com.tripoin.web.view.profile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject.EnumFieldProfile;
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
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = ProfileView.BEAN_NAME, cached = false)
public class ProfileView extends VerticalLayout implements View, ClickListener, Button.ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileView.class);
	public static final String BEAN_NAME = "profile";
	public static final String PAGE_NAME = "Account Settings";

    @Autowired
    private IProfileService profileService;
    
    @Autowired
    private ICommonRest commonRest;
    
    private ProfileData profileData;
    private EmployeeData employeeData;

    private final FormLayout personalInfoFormLayout = new FormLayout();
    private final FormLayout otherInfoFormLayout = new FormLayout();
    private String urlResources;
    private ExternalResource urlImageProfileResource;
    private ProfileImageUploader receiverImage = new ProfileImageUploader();
    private Image profilePhotoImage = new Image();
    private Upload profilePhotoUpload = new Upload(null, receiverImage);
    private final TextField nameTextField = new TextField("Name");
    private final TextField nikTextField = new TextField("NIK");
    private final TextField occupationTextField = new TextField("Occupation");
    private final TextField headTextField = new TextField("Head");
    private final TextField usernameTextField = new TextField("Username");
    private final TextField birthPlaceTextField = new TextField();
    private final DateField birthDateDateField = new DateField();
    private final OptionGroup genderOptionGroup = new OptionGroup("Gender");
    private final TextField phoneTextField = new TextField("Mobile Phone");
    private final TextField telpTextField = new TextField("Home Phone");
    private final TextField emailTextField = new TextField("Email");
    private final TextArea addressTextArea = new TextArea("Address");
    private final RichTextArea bioTextArea = new RichTextArea("Bio");
    private Button submitButton;
    private Label lastModifiedLabel;
    private Notification notification = new Notification("");
    
    @PostConstruct
    public void init() throws Exception {
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout headerLayout = new HorizontalLayout();
        addComponent(headerLayout);
        headerLayout.setMargin(false);
        headerLayout.setWidth("100%");
        final FormLayout formTitle = new FormLayout();       
        headerLayout.addComponent(formTitle);
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label(PAGE_NAME);
        formTitle.addComponent(title); 
        title.addStyleName("h1");   

        final FormLayout formHeaderLayout = new FormLayout();
        addComponent(formHeaderLayout);
        formHeaderLayout.setStyleName("tripoin-custom-form");
        formHeaderLayout.setMargin(false);        
        Label section = new Label(" ");
        formHeaderLayout.addComponent(section); 
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("80%");

        employeeData = profileService.getProfileEmployee();
        profileData = employeeData.getProfileData();
        
        final HorizontalLayout personalInfoLayout = new HorizontalLayout();
        addComponent(personalInfoLayout);
        personalInfoLayout.setMargin(false);
        personalInfoLayout.setSpacing(true);
        personalInfoLayout.setWidth("80%");    
        VerticalLayout groupPhotoUploadLayout = groupPhotoUploadLayout();
        personalInfoLayout.addComponent(groupPhotoUploadLayout);
        personalInfoLayout.setComponentAlignment(groupPhotoUploadLayout, Alignment.MIDDLE_CENTER);
        personalInfoLayout.addComponent(personalInfoFormLayout);
        personalInfoFormLayout.addStyleName("light");
        personalInfoFormLayout.setMargin(false);
        personalInfoFormLayout.setWidth("100%");
        // TODO Personal Info 
        section = new Label("Personal Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("100%");
        personalInfoFormLayout.addComponent(section); 
        personalInfoFormLayout.addComponent(nameTextField);
        nameTextField.setValue(profileData.getName());
        nameTextField.setWidth("100%");
        nameTextField.setRequired(true);

        if(employeeData.getNik() != null){
            personalInfoFormLayout.addComponent(nikTextField);
            nikTextField.setValue(employeeData.getNik());
            nikTextField.setWidth("100%");
            nikTextField.setRequired(true);
            personalInfoFormLayout.addComponent(occupationTextField);
            occupationTextField.setValue(employeeData.getOccupationData().getName());
            occupationTextField.setWidth("100%");
            occupationTextField.setRequired(true);
            if(employeeData.getEmployeeParentData() != null){
                personalInfoFormLayout.addComponent(headTextField);
                headTextField.setValue(employeeData.getEmployeeParentData().getName());
                headTextField.setWidth("100%");
                headTextField.setRequired(true);	
            }        	
        }
        personalInfoFormLayout.addComponent(usernameTextField);
        usernameTextField.setValue(profileData.getUserData().getUsername());
        usernameTextField.setWidth("100%");
        usernameTextField.setRequired(true);
        HorizontalLayout placeDateOfBirth = new HorizontalLayout();
        personalInfoFormLayout.addComponent(placeDateOfBirth);
        placeDateOfBirth.setCaption("Place, Date of Birth");
        placeDateOfBirth.addComponent(birthPlaceTextField); 
        birthPlaceTextField.setRequired(true);
        birthPlaceTextField.setValue(profileData.getBirthplace());
        birthPlaceTextField.setWidth("45%"); 
        placeDateOfBirth.addComponent(birthDateDateField); 
        birthDateDateField.setRequired(true);    
        try {
			birthDateDateField.setValue(ParameterConstant.FORMAT_DEFAULT.parse(profileData.getBirthdate()));
		} catch (ReadOnlyException e) {
			e.printStackTrace();
		} catch (ConversionException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        personalInfoFormLayout.addComponent(genderOptionGroup);
        genderOptionGroup.addItem(ParameterConstant.FEMALE);
        genderOptionGroup.addItem(ParameterConstant.MALE);
        if(ParameterConstant.MALE.equalsIgnoreCase(profileData.getGender()))
        	genderOptionGroup.select(ParameterConstant.MALE);
        else
        	genderOptionGroup.select(ParameterConstant.FEMALE);
        genderOptionGroup.addStyleName("horizontal");
        genderOptionGroup.setRequired(true);
        personalInfoFormLayout.setReadOnly(true);
        nameTextField.setReadOnly(true);
        birthPlaceTextField.setReadOnly(true);
        birthDateDateField.setReadOnly(true);
        usernameTextField.setReadOnly(true);
        nikTextField.setReadOnly(true);
        occupationTextField.setReadOnly(true);
        headTextField.setReadOnly(true);
        genderOptionGroup.setReadOnly(true);
        
        addComponent(otherInfoFormLayout);
        otherInfoFormLayout.addStyleName("light");
        otherInfoFormLayout.setMargin(false);   
        // TODO Contact Info     
        section = new Label("Contact Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        section.setWidth("80%");
        otherInfoFormLayout.addComponent(section);
        otherInfoFormLayout.addComponent(phoneTextField);
        phoneTextField.setWidth("50%");
        phoneTextField.setValue(profileData.getPhone());
        phoneTextField.setRequired(true);
        otherInfoFormLayout.addComponent(telpTextField);
        telpTextField.setWidth("50%");
        telpTextField.setValue(profileData.getTelp());
        otherInfoFormLayout.addComponent(emailTextField);        
        emailTextField.setValue(profileData.getEmail());
        emailTextField.setWidth("50%");
        emailTextField.setRequired(true); 
        otherInfoFormLayout.addComponent(addressTextArea);       
        addressTextArea.setValue(profileData.getAddress());
        addressTextArea.setWidth("50%");
        addressTextArea.setRequired(true);
        // TODO Additional Info
        section = new Label("Additional Info");
        section.addStyleName("h3");
        section.addStyleName("colored");
        otherInfoFormLayout.addComponent(section);        
        bioTextArea.setValue(profileData.getBio());
        bioTextArea.setWidth("100%");
        otherInfoFormLayout.addComponent(bioTextArea);
        // TODO Footer
        submitButton = new Button("Edit", this);
        submitButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        HorizontalLayout footer = new HorizontalLayout();
        otherInfoFormLayout.addComponent(footer);
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        footer.addComponent(submitButton);
        if(profileData.getModifiedTime() != null)
        	lastModifiedLabel = new Label(statusModified(profileData.getModifiedTime()));
        else
        	lastModifiedLabel = new Label();
    	lastModifiedLabel.addStyleName("light");	
    	footer.addComponent(lastModifiedLabel);
        otherInfoFormLayout.setReadOnly(true);
        addressTextArea.setReadOnly(true);
        emailTextField.setReadOnly(true);
        phoneTextField.setReadOnly(true);
        telpTextField.setReadOnly(true);
        bioTextArea.setReadOnly(true);
		
		notification.setCaption("Error");
		notification.setStyleName("system dark small closable");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(10000);
    }
    
    private VerticalLayout groupPhotoUploadLayout(){
    	VerticalLayout groupPhotoUploadLayout = new VerticalLayout();
        groupPhotoUploadLayout.setMargin(false);
        groupPhotoUploadLayout.setWidth("250px");
        Panel profilePhotoPanel = new Panel("Photo Profile");
        groupPhotoUploadLayout.addComponent(profilePhotoPanel);
        profilePhotoPanel.setIcon(FontAwesome.CAMERA);
        profilePhotoPanel.addStyleName("color1");
        profilePhotoPanel.setHeight("288px");
        profilePhotoPanel.setWidth("100%");
        VerticalLayout profilePhotoLayout = new VerticalLayout();
        profilePhotoPanel.setContent(profilePhotoLayout);
        profilePhotoLayout.setSizeFull();
        profilePhotoLayout.setMargin(true);
        profilePhotoLayout.setSpacing(true);
        profilePhotoLayout.addComponent(profilePhotoImage);
        urlResources = commonRest.getUrlHostName(WebServiceConstant.HTTP_RESOURCES_IMAGES.concat("/"));
        String urlImage = urlResources.concat("profile-default-300px.png"); 
        if(profileData.getPhoto() != null)
        	urlImage = urlResources.concat(profileData.getResourcesUUID()).concat("/").concat(profileData.getPhoto());
        urlImageProfileResource = new ExternalResource(urlImage);
        profilePhotoImage.setSource(urlImageProfileResource);
        profilePhotoImage.setSizeFull();
        groupPhotoUploadLayout.addComponent(profilePhotoUpload);
        groupPhotoUploadLayout.setComponentAlignment(profilePhotoUpload, Alignment.MIDDLE_RIGHT);
        profilePhotoUpload.setWidth("100%");
        profilePhotoUpload.setImmediate(true);
        profilePhotoUpload.addStartedListener(new StartedListener() {			
			private static final long serialVersionUID = 1784510326592657108L;
			@Override
			public void uploadStarted(StartedEvent event) {
				String typeFile = event.getMIMEType().split("/")[0];
	        	if(!EWebUIConstant.TYPE_FILE_IMAGE.toString().equals(typeFile)){
	        		event.getUpload().interruptUpload();
					notification.setCaption("Upload Failure");
					notification.setDescription("Type file not valid : ".concat(event.getFilename()));
					notification.show(Page.getCurrent());	        		
	        	}else if(EWebUIConstant.MAX_SIZE_IMAGE_BYTE.getOperatorInt()<event.getContentLength()){
	        		event.getUpload().interruptUpload();
					notification.setCaption("Upload Failure");
					notification.setDescription("Size file more than 1 MB.");
					notification.show(Page.getCurrent());
	        	}
			}
		});
        profilePhotoUpload.addFinishedListener(new FinishedListener() {
			private static final long serialVersionUID = -9184461198940643739L;
			@Override
			public void uploadFinished(FinishedEvent event) {
				BufferedImage bufferedImage = null;
				try {
					if(receiverImage.getFile() != null){
						bufferedImage = ImageIO.read(receiverImage.getFile());
						if(bufferedImage != null){
							if(bufferedImage.getWidth() < 1500){
								Integer x = 0;
								Integer y = 0;
								int width = bufferedImage.getWidth();
								int height = bufferedImage.getHeight();
								if(height<width){
									x = ((width-height)/2);
									width = height;
								}else if(height>width){
									y = ((height-width)/2);
									height = width;
								}
				        		ImageIO.write(bufferedImage.getSubimage(x, y, width, height), receiverImage.getExtensionFile().replace(".", ""), receiverImage.getFile());

								String urlImage = urlResources.concat("profile-default-300px.png");
								IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
								Map<String, Object> data = new HashMap<String, Object>();
								data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE_CREATED_BY, usernameTextField.getValue());
								data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE_CREATED_IP, identifierPlatform.getIPAddress());
								data.put(ParameterConstant.TRIPOIN_UPLOAD_IMAGE_CREATED_PLATFORM, identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
								GeneralTransferObject generalTransferObject = profileService.updatePhotoProfile(receiverImage.getFile(), data);
						        if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode()))
						        	urlImage = urlResources.concat(profileData.getResourcesUUID()).concat("/").concat(receiverImage.getFile().getName());
						        urlImageProfileResource = new ExternalResource(urlImage); 
						        profilePhotoImage.setSource(urlImageProfileResource);
								TripoinUI.get().updateImageProfile(urlImage);
							}
						}						
					}
				} catch (IOException e) {
					LOGGER.warn("IOException : ".concat(e.getLocalizedMessage()));
				}
			}
		});
        profilePhotoUpload.addFailedListener(new FailedListener() {
			private static final long serialVersionUID = 3321334380230456924L;
			@Override
			public void uploadFailed(FailedEvent event) {
				LOGGER.warn("FailedEvent : "+event.getReason().getMessage());
			}
		});
        profilePhotoUpload.addSucceededListener(new SucceededListener() {
			private static final long serialVersionUID = -7061365876392881684L;
			@Override
			public void uploadSucceeded(SucceededEvent event) {
				receiverImage.getFile().delete();
			}
		});
        return groupPhotoUploadLayout;
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

	@Override
	public void click(ClickEvent event) {
		
	}

	@Override
	public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
        nameTextField.setComponentError(null);
        birthPlaceTextField.setComponentError(null);
        birthDateDateField.setComponentError(null);
        addressTextArea.setComponentError(null);
        emailTextField.setComponentError(null);
        phoneTextField.setComponentError(null);
        telpTextField.setComponentError(null);
        bioTextArea.setComponentError(null);
        boolean readOnly = personalInfoFormLayout.isReadOnly();
        if (readOnly) {
        	personalInfoFormLayout.setReadOnly(false);
            otherInfoFormLayout.setReadOnly(false);
            nameTextField.setReadOnly(false);
            birthPlaceTextField.setReadOnly(false);
            birthDateDateField.setReadOnly(false);
            genderOptionGroup.setReadOnly(false);
            addressTextArea.setReadOnly(false);
            emailTextField.setReadOnly(false);
            phoneTextField.setReadOnly(false);
            telpTextField.setReadOnly(false);
            bioTextArea.setReadOnly(false);
            personalInfoFormLayout.removeStyleName("light");
            personalInfoFormLayout.addStyleName("tripoin-custom-form");
            otherInfoFormLayout.removeStyleName("light");
            otherInfoFormLayout.addStyleName("tripoin-custom-form");            
            event.getButton().setCaption("Save");
            event.getButton().addStyleName("primary");
        } else {
        	IdentifierPlatform identifierPlatform = new IdentifierPlatform(Page.getCurrent().getWebBrowser());
        	String allNotif = "";
            boolean isValid = true;
        	if(nameTextField.getValue()==null || nameTextField.getValue().isEmpty()){
        		nameTextField.setComponentError(new UserError("Name not null"));
        		allNotif=allNotif.concat("Name not null!\n");
        		isValid=false;
        	}
        	if(birthPlaceTextField.getValue()==null || birthPlaceTextField.getValue().isEmpty()){
        		birthPlaceTextField.setComponentError(new UserError("Birth place not null"));
        		allNotif=allNotif.concat("Birth place not null!\n");
        		isValid=false;
        	}
        	if(!birthDateDateField.isValid() || birthDateDateField.getValue()==null){
        		birthDateDateField.setComponentError(new UserError("Birth date incorrectly"));
        		allNotif=allNotif.concat("Birth date incorrectly!\n");
        		isValid=false;
        	}
        	if(phoneTextField.getValue()==null || phoneTextField.getValue().isEmpty()){
        		phoneTextField.setComponentError(new UserError("Phone not null"));
        		allNotif=allNotif.concat("Phone not null!\n");
        		isValid=false;
        	}
        	if(telpTextField.getValue()==null || telpTextField.getValue().isEmpty()){
        		telpTextField.setComponentError(new UserError("Telp not null"));
        		allNotif=allNotif.concat("Telp not null!\n");
        		isValid=false;
        	}
        	if(emailTextField.getValue()==null || emailTextField.getValue().isEmpty()){
        		emailTextField.setComponentError(new UserError("Email not null"));
        		allNotif=allNotif.concat("Email not null!\n");
        		isValid=false;
        	}else if (!emailTextField.getValue().matches(EWebUIConstant.REGEX_EMAIL.toString())) {
        		emailTextField.setComponentError(new UserError("Email format not valid"));
        		allNotif=allNotif.concat("Email format not valid!\n");
        		isValid=false;
			}
        	if(addressTextArea.getValue()==null || addressTextArea.getValue().isEmpty()){
        		addressTextArea.setComponentError(new UserError("Address not null"));
        		allNotif=allNotif.concat("Address not null!\n");
        		isValid=false;
        	}	
        	if(isValid){
        		GeneralTransferObject generalTransferObject  = new GeneralTransferObject();
        		HashMap<String, Object> dataFieldMap = new HashMap<String, Object>();        		
        		dataFieldMap.put(EnumFieldProfile.NAME_PROFILE.toString(), nameTextField.getValue());
        		dataFieldMap.put(EnumFieldProfile.BIRTHPLACE_PROFILE.toString(), birthPlaceTextField.getValue());
        		dataFieldMap.put(EnumFieldProfile.BIRTHDATE_PROFILE.toString(), ParameterConstant.FORMAT_DEFAULT.format(birthDateDateField.getValue()));
        		dataFieldMap.put(EnumFieldProfile.GENDER_PROFILE.toString(), genderOptionGroup.getValue().toString());
        		dataFieldMap.put(EnumFieldProfile.ADDRESS_PROFILE.toString(), addressTextArea.getValue());
        		dataFieldMap.put(EnumFieldProfile.PHONE_PROFILE.toString(), phoneTextField.getValue());
        		dataFieldMap.put(EnumFieldProfile.TELP_PROFILE.toString(), telpTextField.getValue());
        		dataFieldMap.put(EnumFieldProfile.EMAIL_PROFILE.toString(), emailTextField.getValue());
        		dataFieldMap.put(EnumFieldProfile.BIO_PROFILE.toString(), bioTextArea.getValue());
        		dataFieldMap.put(ParameterConstant.IDENTIFIER_IP.toString(), identifierPlatform.getIPAddress());
            	dataFieldMap.put(ParameterConstant.IDENTIFIER_TIME.toString(), ParameterConstant.FORMAT_DEFAULT.format(new Date()));            	
            	dataFieldMap.put(ParameterConstant.IDENTIFIER_PLATFORM.toString(), identifierPlatform.getDevice().concat(" | ").concat(identifierPlatform.getOperatingSystem()).concat(" | ").concat(identifierPlatform.getBrowser()));
            	generalTransferObject.setParameterData(dataFieldMap);
            	generalTransferObject = profileService.updateProfile(generalTransferObject);
            	dataFieldMap = null;
            	if(EResponseCode.RC_FAILURE.getResponseCode().equals(generalTransferObject.getResponseCode())){
            		notification.setDescription("Update account error, please try again later!");
        	        notification.show(Page.getCurrent());	            		
            	}else if(EResponseCode.RC_PHONE_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
            		phoneTextField.setComponentError(new UserError(EResponseCode.RC_PHONE_EXISTS.toString()));
            		notification.setDescription(EResponseCode.RC_PHONE_EXISTS.toString());
        	        notification.show(Page.getCurrent());	            		
            	}else if(EResponseCode.RC_EMAIL_EXISTS.getResponseCode().equals(generalTransferObject.getResponseCode())){
            		emailTextField.setComponentError(new UserError(EResponseCode.RC_EMAIL_EXISTS.toString()));
            		notification.setDescription(EResponseCode.RC_EMAIL_EXISTS.toString());
        	        notification.show(Page.getCurrent());	            		
            	}else{
                    nameTextField.setComponentError(null);
                    birthPlaceTextField.setComponentError(null);
                    birthDateDateField.setComponentError(null);
                    addressTextArea.setComponentError(null);
                    emailTextField.setComponentError(null);
                    phoneTextField.setComponentError(null);
                    telpTextField.setComponentError(null);
                    bioTextArea.setComponentError(null);
                    personalInfoFormLayout.setReadOnly(true);
                    otherInfoFormLayout.setReadOnly(true);
                    nameTextField.setReadOnly(true);
                    birthPlaceTextField.setReadOnly(true);
                    birthDateDateField.setReadOnly(true);
                    genderOptionGroup.setReadOnly(true);
                    addressTextArea.setReadOnly(true);
                    emailTextField.setReadOnly(true);
                    phoneTextField.setReadOnly(true);
                    telpTextField.setReadOnly(true);
                    bioTextArea.setReadOnly(true);
                    lastModifiedLabel.setValue(statusModified(profileData.getModifiedTime()));
                    personalInfoFormLayout.addStyleName("light");
                    personalInfoFormLayout.removeStyleName("tripoin-custom-form");
                    otherInfoFormLayout.addStyleName("light");
                    otherInfoFormLayout.removeStyleName("tripoin-custom-form");
                    event.getButton().setCaption("Edit");
                    event.getButton().removeStyleName("primary");
            	}
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
