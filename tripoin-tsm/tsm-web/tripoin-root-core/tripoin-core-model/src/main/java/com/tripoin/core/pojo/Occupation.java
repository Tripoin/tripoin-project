package com.tripoin.core.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="mst_occupation")
public class Occupation implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
	private Integer id;
    private String code;
    private String name;
    private Integer status;
    private String remarks;
    private String createdBy;
    private String createdIP;
    private Date createdTime;
    private String createdPlatform;
    private String modifiedBy;
    private String modifiedIP;
    private Date modifiedTime;
    private String modifiedPlatform;
    private List<Employee> employees;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="occupation_id")
    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="occupation_code", length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="occupation_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="occupation_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="occupation_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="occupation_created_by", length=150)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="occupation_created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="occupation_created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="occupation_created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="occupation_modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="occupation_modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="occupation_modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="occupation_modified_platform")
    public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}
	
	@OneToMany(mappedBy = "occupation", cascade=CascadeType.ALL)
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Occupation [id=" + id + ", code=" + code + ", name=" + name
				+ ", status=" + status 
				+ ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime
				+ ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP
				+ ", modifiedTime=" + modifiedTime + ", modifiedPlatform=" + modifiedPlatform + "]";
	}
	
}