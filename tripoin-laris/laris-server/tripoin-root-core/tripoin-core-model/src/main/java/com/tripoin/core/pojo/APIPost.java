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
@Table(name=APIPost.TABLE_NAME)
public class APIPost extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "trx_api_post";

	private Integer id;
    private String media;
    private String description;
    private Timestamp createdPostDate;
    private Timestamp modifiedPostDate;
    private LinkedAccount linkedAccount;
    private MediaType mediaType;

    public APIPost() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="api_post_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="api_post_media")
	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	@Column(name="api_post_description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="api_post_created_date")
	public Timestamp getCreatedPostDate() {
		return createdPostDate;
	}

	public void setCreatedPostDate(Timestamp createdPostDate) {
		this.createdPostDate = createdPostDate;
	}

	@Column(name="api_post_modified_date")
	public Timestamp getModifiedPostDate() {
		return modifiedPostDate;
	}

	public void setModifiedPostDate(Timestamp modifiedPostDate) {
		this.modifiedPostDate = modifiedPostDate;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name = "linked_account_id", nullable = false)
	public LinkedAccount getLinkedAccount() {
		return linkedAccount;
	}

	public void setLinkedAccount(LinkedAccount linkedAccount) {
		this.linkedAccount = linkedAccount;
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
		return "APIPost [id=" + id + ", media=" + media + ", description="
				+ description + ", createdPostDate=" + createdPostDate
				+ ", modifiedPostDate=" + modifiedPostDate + ", linkedAccount="
				+ linkedAccount + ", mediaType=" + mediaType + ", auditTrail=" 
				+ super.toString() + "]";
	}
	
}
