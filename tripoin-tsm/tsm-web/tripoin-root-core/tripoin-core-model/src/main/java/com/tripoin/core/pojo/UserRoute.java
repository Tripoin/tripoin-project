package com.tripoin.core.pojo;

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
@Table(name=UserRoute.TABLE_NAME)
public class UserRoute implements IBaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
    public static final String TABLE_NAME = "geo_user_route";
	
	private Integer id;
    private Double latitude;
    private Double longitude;
    private Integer center;
    private Integer zoom;
    private Integer drag;
    private String marker;
    private String caption;
    private String icon;
    private String createdBy;
    private String createdIP;
    private Date createdTime;
    private String createdPlatform;
    private String modifiedBy;
    private String modifiedIP;
    private Date modifiedTime;
    private String modifiedPlatform;
    private Employee employee;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_route_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="user_route_lat")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name="user_route_lon")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name="user_route_center")
	public Integer getCenter() {
		return center;
	}

	public void setCenter(Integer center) {
		this.center = center;
	}

	@Column(name="user_route_zoom")
	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	@Column(name="user_route_drag")
	public Integer getDrag() {
		return drag;
	}

	public void setDrag(Integer drag) {
		this.drag = drag;
	}

	@Column(name="user_route_marker", length=255)
	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	@Column(name="user_route_caption", length=255)
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name="user_route_icon", length=255)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name="user_route_created_by", length=150)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="user_route_created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="user_route_created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="user_route_created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="user_route_modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="user_route_modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="user_route_modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="user_route_modified_platform")
    public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}
	
	@ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "UserRoute [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", center=" + center + ", zoom="
				+ zoom + ", drag=" + drag + ", marker=" + marker + ", caption="
				+ caption + ", icon=" + icon + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime
				+ ", createdPlatform=" + createdPlatform + ", modifiedBy="
				+ modifiedBy + ", modifiedIP=" + modifiedIP + ", modifiedTime="
				+ modifiedTime + ", modifiedPlatform=" + modifiedPlatform
				+ ", employee=" + employee + "]";
	}

}
