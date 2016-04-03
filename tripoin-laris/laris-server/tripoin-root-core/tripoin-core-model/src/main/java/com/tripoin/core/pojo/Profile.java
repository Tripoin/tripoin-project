package com.tripoin.core.pojo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=Profile.TABLE_NAME)
public class Profile extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
    public static final String TABLE_NAME = "mst_profile";

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
    private Timestamp forgotExpired;
    private User user;
    private ZipCode zipCode;
    private Country country;
    
    public Profile() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id")
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
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="profile_gender", length=10)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="profile_birthplace", length=150)
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(name="profile_birthdate")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = new Date(birthdate.getTime());
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
	public Timestamp getForgotExpired() {
		return forgotExpired;
	}

	public void setForgotExpired(Timestamp forgotExpired) {
		this.forgotExpired = forgotExpired;
	}

	public void setForgotExpired(java.util.Date forgotExpired) {
		this.forgotExpired = new Timestamp(forgotExpired.getTime());
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
    @JoinColumn(name = "zipcode_id")
	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	@ManyToOne
    @JoinColumn(name = "country_id")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", email=" + email + ", name=" + name + ", gender=" + gender + ", birthplace="
				+ birthplace + ", birthdate=" + birthdate + ", address=" + address + ", telp=" + telp + ", phone="
				+ phone + ", photo=" + photo + ", bio=" + bio + ", resourcesUUID=" + resourcesUUID + ", forgotUUID="
				+ forgotUUID + ", forgotExpired=" + forgotExpired + ", user.id=" + user.getId() + ", zipCode="
				+ zipCode + ", country=" + country
				+ ", auditTrail=" + super.toString() + "]";
	}
	
}
