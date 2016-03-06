package com.tripoin.core.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.UserRoute;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UserRouteData")
public class UserRouteData {
	
	@XmlElement(name = "Latitude", namespace = "")
	private Double latitude;
	
	@XmlElement(name = "Longitude", namespace = "")
	private Double longitude;
	
	@XmlElement(name = "Center", namespace = "")
	private Integer center;
	
	@XmlElement(name = "Zoom", namespace = "")
	private Integer zoom;
	
	@XmlElement(name = "Drag", namespace = "")
	private Integer drag;
	
	@XmlElement(name = "Marker", namespace = "")
	private String marker;
	
	@XmlElement(name = "Caption", namespace = "")
	private String caption;
	
	@XmlElement(name = "Icon", namespace = "")
	private String icon;
	
	@XmlElement(name = "CreatedBy", namespace = "")
    private String createdBy;
	
	@XmlElement(name = "CreatedIP", namespace = "")
    private String createdIP;
	
	@XmlElement(name = "CreatedTime", namespace = "")
    private String createdTime;
	
	@XmlElement(name = "CreatedPlatform", namespace = "")
    private String createdPlatform;
	
	@XmlElement(name = "ModifiedBy", namespace = "")
    private String modifiedBy;
	
	@XmlElement(name = "ModifiedIP", namespace = "")
    private String modifiedIP;
	
	@XmlElement(name = "ModifiedTime", namespace = "")
    private String modifiedTime;
	
	@XmlElement(name = "ModifiedPlatform", namespace = "")
    private String modifiedPlatform;
	
	@XmlElement(name = "EmployeeData", namespace = "")
	private EmployeeData employeeData;

	public UserRouteData(UserRoute userRoute) {
		super();
		if(userRoute != null){
			this.latitude = userRoute.getLatitude();
			this.longitude = userRoute.getLongitude();
			this.center = userRoute.getCenter();
			this.zoom = userRoute.getZoom();
			this.drag = userRoute.getDrag();
			this.marker = userRoute.getMarker();
			this.caption = userRoute.getCaption();
			this.icon = userRoute.getIcon();
			this.createdBy = userRoute.getCreatedBy();
			this.createdIP = userRoute.getCreatedIP();
			if(userRoute.getCreatedTime() != null)
				this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(userRoute.getCreatedTime());
			this.createdPlatform = userRoute.getCreatedPlatform();
			this.modifiedBy = userRoute.getModifiedBy();
			this.modifiedIP = userRoute.getModifiedIP();
			if(userRoute.getCreatedTime() != null)
				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(userRoute.getModifiedTime());
			this.modifiedPlatform = userRoute.getModifiedPlatform();
			if(userRoute.getEmployee() != null)
				this.employeeData = new EmployeeData(userRoute.getEmployee());
		}
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getCenter() {
		return center;
	}

	public void setCenter(Integer center) {
		this.center = center;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Integer getDrag() {
		return drag;
	}

	public void setDrag(Integer drag) {
		this.drag = drag;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	public EmployeeData getEmployeeData() {
		return employeeData;
	}

	public void setEmployeeData(EmployeeData employeeData) {
		this.employeeData = employeeData;
	}

	@Override
	public String toString() {
		return "UserRouteData [latitude=" + latitude + ", longitude=" + longitude + ", center=" + center + ", zoom="
				+ zoom + ", drag=" + drag + ", marker=" + marker + ", caption=" + caption + ", icon=" + icon
				+ ", createdBy=" + createdBy + ", createdIP=" + createdIP + ", createdTime=" + createdTime
				+ ", createdPlatform=" + createdPlatform + ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP
				+ ", modifiedTime=" + modifiedTime + ", modifiedPlatform=" + modifiedPlatform + ", employeeData="
				+ employeeData + "]";
	}

}
