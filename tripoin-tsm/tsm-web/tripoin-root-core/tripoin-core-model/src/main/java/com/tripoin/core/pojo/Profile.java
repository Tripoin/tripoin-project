package com.tripoin.core.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.ProfileData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="mst_profile")
public class Profile implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
	private Integer id;
    private String email;
    private String name;
    private String gender;
    private String birthplace;
    private Date birthdate;
    private String address;
    private String telp;
    private String phone;
    private String photo;
    private String bio;
    private String resourcesUUID;
    private String forgotUUID;
    private Date forgotExpired;
    private String createdBy;
    private String createdIP;
    private Date createdTime;
    private String createdPlatform;
    private String modifiedBy;
    private String modifiedIP;
    private Date modifiedTime; 
    private String modifiedPlatform;
    private User user;	
    private Employee employee;
    
    public Profile() {}
    
    public Profile(ProfileData profileData) {
    	if(profileData != null){
    		this.id = profileData.getId();
    		this.email = profileData.getEmail();
    		this.name = profileData.getName();
    		this.gender = profileData.getGender();
    		this.birthplace = profileData.getBirthplace();
    		try {
				this.birthdate = ParameterConstant.FORMAT_DEFAULT.parse(profileData.getBirthdate());
			} catch (ParseException e) {
				this.birthdate = new Date();
			}
    		this.address = profileData.getAddress();
    		this.telp = profileData.getTelp();
    		this.phone = profileData.getPhone();
    		this.photo = profileData.getPhoto();
    		this.bio = profileData.getBio();
    		this.resourcesUUID = profileData.getResourcesUUID();
    		this.forgotUUID = profileData.getForgotUUID();
    		try {
    			if(profileData.getForgotExpired() != null)
    				this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.parse(profileData.getForgotExpired());
			} catch (ParseException e) {
				this.forgotExpired = new Date();
			}
    		this.createdBy = profileData.getCreatedBy();
    		this.createdIP = profileData.getCreatedIP();
    		try {
    			if(profileData.getCreatedTime() != null)
    				this.createdTime = ParameterConstant.FORMAT_DEFAULT.parse(profileData.getCreatedTime());
			} catch (ParseException e) {
				this.createdTime = new Date();
			}
    		this.createdPlatform = profileData.getCreatedPlatform();
    		this.modifiedBy = profileData.getModifiedBy();
    		this.modifiedIP = profileData.getModifiedIP();
    		try {
    			if(profileData.getModifiedTime() != null)
    				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.parse(profileData.getModifiedTime());
			} catch (ParseException e) {
				this.modifiedTime = new Date();
			}
    		this.modifiedPlatform = profileData.getModifiedPlatform();
    		if(profileData.getUserData() != null)
				this.user = new User(profileData.getUserData());
    	}
    }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id")
    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="profile_email", unique=true, length=150)
    @NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 

	@Column(name="profile_name", length=150)
    @NotNull
    @Size(min = 5, message = "Name must have at least five characters")
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="profile_gender", length=10)
    @NotNull
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="profile_birthplace", length=150)
    @NotNull
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name="profile_birthdate")
    @NotNull
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name="profile_address")
    @NotNull
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="profile_telp", length=20)
	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	@Column(name="profile_phone", unique=true, length=20)
    @NotNull
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name="profile_photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name="profile_bio")
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Column(name="profile_resources_uuid")
	public String getResourcesUUID() {
		return resourcesUUID;
	}

	public void setResourcesUUID(String resourcesUUID) {
		this.resourcesUUID = resourcesUUID;
	}

	@Column(name="profile_forgot_uuid")
	public String getForgotUUID() {
		return forgotUUID;
	}

	public void setForgotUUID(String forgotUUID) {
		this.forgotUUID = forgotUUID;
	}

	@Column(name="profile_forgot_expired")
	public Date getForgotExpired() {
		return forgotExpired;
	}

	public void setForgotExpired(Date forgotExpired) {
		this.forgotExpired = forgotExpired;
	}

	@Column(name="profile_created_by", length=150)
    public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="profile_created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="profile_created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="profile_created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="profile_modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="profile_modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="profile_modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	@Column(name="profile_modified_platform")
    public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@OneToOne
    @JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}       

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile", cascade = CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", email=" + email + ", name=" + name
				+ ", birthplace=" + birthplace + ", birthdate=" + birthdate
				+ ", address=" + address + ", telp=" + telp + ", phone=" + phone
				+ ", photo=" + photo + ", bio=" + bio + ", resourcesUUID=" + resourcesUUID + ", forgotUUID=" + forgotUUID
				+ ", forgotExpired=" + forgotExpired + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime
				+ ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP
				+ ", modifiedTime=" + modifiedTime + ", modifiedPlatform=" + modifiedPlatform
				+ ", user=" + user + "]";
	} 
	
}
