package com.tripoin.core.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=LinkedAccount.TABLE_NAME)
public class LinkedAccount extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_linked_account";

	private Integer id;
    private String code;
    private String name;
    private String username;
    private String photo;
    private String authorization;
    private String token;
    private String userCredentials;
    private APIType apiType;
    private Profile profile;

    public LinkedAccount() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="linked_account_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="linked_account_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="linked_account_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="linked_account_username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="linked_account_photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name="linked_account_authorization")
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	@Column(name="linked_account_token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name="linked_account_user_credentials")
	public String getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(String userCredentials) {
		this.userCredentials = userCredentials;
	}

	@ManyToOne
    @JoinColumn(name = "api_type_id")
	public APIType getApiType() {
		return apiType;
	}

	public void setApiType(APIType apiType) {
		this.apiType = apiType;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name = "profile_id", nullable = false)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "LinkedAccount [id=" + id + ", code=" + code + ", name=" + name
				+ ", username=" + username + ", photo=" + photo
				+ ", authorization=" + authorization + ", token=" + token
				+ ", userCredentials=" + userCredentials + ", apiType="
				+ apiType + ", profile.id=" + profile.getId() + "]";
	}	
	
}