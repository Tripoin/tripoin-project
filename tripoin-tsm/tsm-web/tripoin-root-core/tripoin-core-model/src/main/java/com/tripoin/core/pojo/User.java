package com.tripoin.core.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=User.TABLE_NAME)
public class User implements IBaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5005939533336216605L;
    public static final String TABLE_NAME = "sec_user";
	
	private Integer id;
    private String username;
    private String password;
    private Integer enabled;
    private Timestamp expiredDate;
    private Integer nonLocked;
    private String auth;
    private Integer status;
    private String remarks;
    private Role role;
    private List<VersionControlSystemUser> versionControlSystemUser;
    private Profile profile;
 
    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_username", unique=true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }    

    @Column(name = "user_password", length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_enabled")
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Column(name = "user_expired_date")
    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Column(name = "user_non_locked")
    public Integer getNonLocked() {
        return nonLocked;
    }

    public void setNonLocked(Integer nonLocked) {
        this.nonLocked = nonLocked;
    }

    @Column(name = "user_auth", length = 255)
    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Column(name = "user_status")
    public Integer getStatus() {
        return status;
    }   
	
	public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "user_remarks", length = 255)
    public String getRemarks() {
        return remarks;
    }  

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
	
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }       

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public List<VersionControlSystemUser> getVersionFilter() {
        return versionControlSystemUser;
    }

    public void setVersionFilter(List<VersionControlSystemUser> versionControlSystemUser) {
        this.versionControlSystemUser = versionControlSystemUser;
    }

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password="
                + password + ", enabled=" + enabled + ", expiredDate="
                + expiredDate + ", nonLocked=" + nonLocked + ", auth=" + auth
                + ", status=" + status + ", remarks=" + remarks + ", role="
                + role + "]";
    }

}
