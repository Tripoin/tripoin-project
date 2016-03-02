package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Profile;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProfileData")
public class ProfileData extends AGeneralAuditTrailData {

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
	
	@XmlElement(name = "UserData", namespace = "")
	private UserData userData;

	public ProfileData() {}
	
	public ProfileData(Profile profile){
		super(profile);
		if(profile != null){
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
				this.forgotExpired = ParameterConstant.FORMAT_DEFAULT.format(profile.getForgotExpired());
		}
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

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((birthplace == null) ? 0 : birthplace.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((forgotExpired == null) ? 0 : forgotExpired.hashCode());
		result = prime * result + ((forgotUUID == null) ? 0 : forgotUUID.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((resourcesUUID == null) ? 0 : resourcesUUID.hashCode());
		result = prime * result + ((telp == null) ? 0 : telp.hashCode());
		result = prime * result + ((userData == null) ? 0 : userData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileData other = (ProfileData) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (birthplace == null) {
			if (other.birthplace != null)
				return false;
		} else if (!birthplace.equals(other.birthplace))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (forgotExpired == null) {
			if (other.forgotExpired != null)
				return false;
		} else if (!forgotExpired.equals(other.forgotExpired))
			return false;
		if (forgotUUID == null) {
			if (other.forgotUUID != null)
				return false;
		} else if (!forgotUUID.equals(other.forgotUUID))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (resourcesUUID == null) {
			if (other.resourcesUUID != null)
				return false;
		} else if (!resourcesUUID.equals(other.resourcesUUID))
			return false;
		if (telp == null) {
			if (other.telp != null)
				return false;
		} else if (!telp.equals(other.telp))
			return false;
		if (userData == null) {
			if (other.userData != null)
				return false;
		} else if (!userData.equals(other.userData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfileData [id=" + id + ", email=" + email + ", name=" + name + ", gender=" + gender + ", birthplace="
				+ birthplace + ", birthdate=" + birthdate + ", address=" + address + ", telp=" + telp + ", phone="
				+ phone + ", photo=" + photo + ", bio=" + bio + ", resourcesUUID=" + resourcesUUID + ", forgotUUID="
				+ forgotUUID + ", forgotExpired=" + forgotExpired + ", userData=" + userData + "]";
	}

}
