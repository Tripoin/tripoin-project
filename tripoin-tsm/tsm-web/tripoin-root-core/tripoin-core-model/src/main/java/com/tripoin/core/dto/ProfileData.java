package com.tripoin.core.dto;

import java.util.Date;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.User;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProfileData {

	private Integer id;
	private String email;
	private String name;
	private String gender;
	private String birthplace;
	private String birthdate;
	private String address;
	private String telp;
	private String phone;
	private String photo;
	private String bio;
    private String forgotUUID;
    private String forgotExpired;
	private String createdBy;
	private String createdIP;
	private String createdTime;
    private String createdPlatform;
	private String modifiedBy;
	private String modifiedIP;
	private String modifiedTime;
    private String modifiedPlatform;
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
		this.forgotUUID = profile.getForgotUUID();
		if(profile.getForgotExpired() != null)
			this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.format(profile.getModifiedTime());
		this.userData = new UserData(profile.getUser());
		this.createdBy = profile.getCreatedBy();
		this.createdIP = profile.getCreatedIP();
		this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(profile.getCreatedTime());
		this.createdPlatform = profile.getCreatedPlatform();
		this.modifiedBy = profile.getModifiedBy();
		this.modifiedIP = profile.getModifiedIP();
		if(profile.getModifiedTime() != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(profile.getModifiedTime());
		this.modifiedPlatform = profile.getModifiedPlatform();
	}
	
	public ProfileData(int id, String email, String name, String gender,
			String birthplace, Date birthdate, String address, String telp,
			String phone, String photo, String bio, String forgotUUID, Date forgotExpired,
			User user, String createdBy, String createdIP,
			Date createdTime, String createdPlatform, 
			String modifiedBy, String modifiedIP, Date modifiedTime, String modifiedPlatform) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.birthplace = birthplace;
		this.birthdate = ParameterConstant.FORMAT_DEFAULT.format(birthdate);
		this.address = address;
		this.telp = telp;
		this.phone = phone;
		this.photo = photo;
		this.bio = bio;
		this.forgotUUID = forgotUUID;
		if(forgotExpired != null)
			this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.format(forgotExpired);
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(createdTime);
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		if(modifiedTime != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(modifiedTime);
		this.modifiedPlatform = modifiedPlatform;
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
				+ ", bio=" + bio + ", forgotUUID=" + forgotUUID + ", forgotExpired=" + forgotExpired
				+ ", createdBy=" + createdBy + ", createdIP="
				+ createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform + ", userData=" + userData + "]";
	}

}
