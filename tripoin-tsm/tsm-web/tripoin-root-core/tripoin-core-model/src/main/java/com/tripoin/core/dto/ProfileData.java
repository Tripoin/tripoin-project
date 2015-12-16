package com.tripoin.core.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProfileData")
public class ProfileData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;
	
	@XmlElement(name = "Email", namespace = "")
	private String email;
	
	@XmlElement(name = "Name", namespace = "")
	private String name;
	
	@XmlElement(name = "Gender", namespace = "")
	private String gender;
	
	@XmlElement(name = "Birthplace", namespace = "")
	private String birthplace;
	
	@XmlElement(name = "Birthdate", namespace = "")
	private String birthdate;
	
	@XmlElement(name = "Address", namespace = "")
	private String address;
	
	@XmlElement(name = "Telp", namespace = "")
	private String telp;
	
	@XmlElement(name = "Phone", namespace = "")
	private String phone;
	
	@XmlElement(name = "Photo", namespace = "")
	private String photo;
	
	@XmlElement(name = "Bio", namespace = "")
	private String bio;
	
	@XmlElement(name = "ResourcesUUID", namespace = "")
	private String resourcesUUID;
	
	@XmlElement(name = "ForgotUUID", namespace = "")
    private String forgotUUID;
	
	@XmlElement(name = "ForgotExpired", namespace = "")
    private String forgotExpired;
	
	@XmlElement(name = "CreatedBy", namespace = "")
	private String createdBy;
	
	@XmlElement(name = "CreatedIP", namespace = "")
	private String createdIP;
	
	@XmlElement(name = "CreatedTime", namespace = "")
	private String createdTime;
	
	@XmlElement(name = "CreatedPlatform", namespace = "")
    private String createdPlatform;
	
	@XmlElement(name = "ModifiedBy", namespace = "")
	private String modifiedBy;
	
	@XmlElement(name = "ModifiedIP", namespace = "")
	private String modifiedIP;
	
	@XmlElement(name = "ModifiedTime", namespace = "")
	private String modifiedTime;
	
	@XmlElement(name = "ModifiedPlatform", namespace = "")
    private String modifiedPlatform;
	
	@XmlElement(name = "UserData", namespace = "")
	private UserData userData;

	public ProfileData() {}
	
	public ProfileData(Profile profile){
		super();
		this.id = profile.getId();
		this.email = profile.getEmail();
		this.name = profile.getName();
		this.gender = profile.getGender();
		this.birthplace = profile.getBirthplace();
		if(profile.getBirthdate() != null)
			this.birthdate = ParameterConstant.FORMAT_DEFAULT.format(profile.getBirthdate());
		this.address = profile.getAddress();
		this.telp = profile.getTelp();
		this.phone = profile.getPhone();
		this.photo = profile.getPhoto();
		this.bio = profile.getBio();
		this.resourcesUUID = profile.getResourcesUUID();
		this.forgotUUID = profile.getForgotUUID();
		if(profile.getForgotExpired() != null)
			this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.format(profile.getModifiedTime());
		this.createdBy = profile.getCreatedBy();
		this.createdIP = profile.getCreatedIP();
		if(profile.getCreatedTime() != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(profile.getCreatedTime());
		this.createdPlatform = profile.getCreatedPlatform();
		this.modifiedBy = profile.getModifiedBy();
		this.modifiedIP = profile.getModifiedIP();
		if(profile.getModifiedTime() != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(profile.getModifiedTime());
		this.modifiedPlatform = profile.getModifiedPlatform();
		if(profile.getUser() != null)
			this.userData = new UserData(profile.getUser());
	}
	
	public ProfileData(int id, String email, String name, String gender,
			String birthplace, Date birthdate, String address, String telp,
			String phone, String photo, String bio, String resourcesUUID, String forgotUUID, Date forgotExpired,
			User user, String createdBy, String createdIP,
			Date createdTime, String createdPlatform, 
			String modifiedBy, String modifiedIP, Date modifiedTime, String modifiedPlatform) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.birthplace = birthplace;
		if(birthdate != null)
			this.birthdate = ParameterConstant.FORMAT_DEFAULT.format(birthdate);
		this.address = address;
		this.telp = telp;
		this.phone = phone;
		this.photo = photo;
		this.bio = bio;
		this.resourcesUUID = resourcesUUID;
		this.forgotUUID = forgotUUID;
		if(forgotExpired != null)
			this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.format(forgotExpired);
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		if(createdTime != null)
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(createdTime);
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		if(modifiedTime != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(modifiedTime);
		this.modifiedPlatform = modifiedPlatform;
		if(user != null)
			this.userData = new UserData(user);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getResourcesUUID() {
		return resourcesUUID;
	}

	public void setResourcesUUID(String resourcesUUID) {
		this.resourcesUUID = resourcesUUID;
	}

	public String getForgotUUID() {
		return forgotUUID;
	}

	public void setForgotUUID(String forgotUUID) {
		this.forgotUUID = forgotUUID;
	}

	public String getForgotExpired() {
		return forgotExpired;
	}

	public void setForgotExpired(String forgotExpired) {
		this.forgotExpired = forgotExpired;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "ProfileData [id=" + id + ", email=" + email + ", name=" + name
				+ ", gender=" + gender + ", birthplace=" + birthplace
				+ ", birthdate=" + birthdate + ", address=" + address
				+ ", telp=" + telp + ", phone=" + phone + ", photo=" + photo
				+ ", bio=" + bio + ", resourcesUUID=" + resourcesUUID 
				+ ", forgotUUID=" + forgotUUID + ", forgotExpired=" + forgotExpired
				+ ", createdBy=" + createdBy + ", createdIP="
				+ createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform + ", userData=" + userData + "]";
	}

}
