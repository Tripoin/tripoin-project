package com.tripoin.core.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.EmployeeData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="mst_employee")
public class Employee implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
	private Integer id;
    private String code;
    private String nik;
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
    private Profile profile;
    private Occupation occupation;
    private Employee employeeParent;
    private List<UserRoute> userRoutes;

    public Employee() {}
    
	public Employee(EmployeeData employeeData) {
		super();
		this.id = employeeData.getId();
		this.code = employeeData.getCode();
		this.nik = employeeData.getNik();
		this.status = employeeData.getStatus();
		this.remarks = employeeData.getRemarks();
		this.createdBy = employeeData.getCreatedBy();
		this.createdIP = employeeData.getCreatedIP();
		try {
			if(employeeData.getCreatedTime() != null)
				this.createdTime = ParameterConstant.FORMAT_DEFAULT.parse(employeeData.getCreatedTime());
		} catch (ParseException e) {
			this.createdTime = new Date();
		}
		this.createdPlatform = employeeData.getCreatedPlatform();
		this.modifiedBy = employeeData.getModifiedBy();
		this.modifiedIP = employeeData.getModifiedIP();
		try {
			if(employeeData.getModifiedTime() != null)
				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.parse(employeeData.getModifiedTime());
		} catch (ParseException e) {
			this.modifiedTime = new Date();
		}
		this.modifiedPlatform = employeeData.getModifiedPlatform();
		this.profile = new Profile(employeeData.getProfileData());
		this.occupation = new Occupation(employeeData.getOccupationData());
		if(employeeData.getEmployeeDataParent() != null)
			this.employeeParent = new Employee(employeeData.getEmployeeDataParent());
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id")
    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="employee_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="employee_nik", unique=true, length=150)
	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	@Column(name="employee_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="employee_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="employee_created_by", length=150)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="employee_created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="employee_created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="employee_created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="employee_modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="employee_modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="employee_modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="employee_modified_platform")
    public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@OneToOne
    @JoinColumn(name = "profile_id", nullable = false)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

    @ManyToOne
    @JoinColumn(name = "occupation_id", nullable = false)
    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }    

    @ManyToOne
    @JoinColumn(name = "employee_parent_id")
	public Employee getEmployeeParent() {
		return employeeParent;
	}

	public void setEmployeeParent(Employee employeeParent) {
		this.employeeParent = employeeParent;
	}

	@OneToMany(mappedBy = "employee", cascade=CascadeType.ALL)
	public List<UserRoute> getUserRoutes() {
		return userRoutes;
	}

	public void setUserRoutes(List<UserRoute> userRoutes) {
		this.userRoutes = userRoutes;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", code=" + code + ", nik=" + nik
				+ ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP
				+ ", createdTime=" + createdTime + ", createdPlatform="
				+ createdPlatform + ", modifiedBy=" + modifiedBy
				+ ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform
				+ ", profile=" + profile + ", occupation=" + occupation
				+ ", employeeParent=" + employeeParent + "]";
	}
	
}
