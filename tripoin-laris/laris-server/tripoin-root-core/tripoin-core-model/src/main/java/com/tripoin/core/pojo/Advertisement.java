package com.tripoin.core.pojo;

import java.sql.Timestamp;

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
@Table(name=Advertisement.TABLE_NAME)
public class Advertisement extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_ads";

	private Integer id;
    private String code;
    private String media;
    private String detail;
    private Timestamp expired;
    private Integer enabled;
    private Profile profile;
    private MediaType mediaType; 

    public Advertisement() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ads_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="ads_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="ads_media")
	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Column(name="ads_detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name="ads_expired")
	public Timestamp getExpired() {
		return expired;
	}

	public void setExpired(Timestamp expired) {
		this.expired = expired;
	}
	
	@Column(name="ads_enabled")
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name = "profile_id", nullable = false)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@ManyToOne
    @JoinColumn(name = "media_type_id")
	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", code=" + code + ", media="
				+ media + ", detail=" + detail + ", expired=" + expired
				+ ", enabled=" + enabled + ", profile.id=" + profile.getId()
				+ ", mediaType=" + mediaType + "]";
	}
}
