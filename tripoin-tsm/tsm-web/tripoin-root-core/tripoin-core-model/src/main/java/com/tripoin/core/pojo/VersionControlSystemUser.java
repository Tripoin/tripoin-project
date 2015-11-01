package com.tripoin.core.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="vcs_user")
public class VersionControlSystemUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7393825537040401410L;
	private Integer id;
	private User user;
	private Date version;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="vcs_user_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="vcs_user_version")
	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "VersionFilter [id=" + id + ", user=" + user + ", version=" + version + "]";
	}
	
}
