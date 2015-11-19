package com.tripoin.core.dto;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.pojo.Menu;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MenuData")
public class MenuData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;
	
	@XmlElement(name = "Code", namespace = "")
    private String code;
	
	@XmlElement(name = "Name", namespace = "")
    private String name;
	
	@XmlElement(name = "MenudDataParent", namespace = "")
    private MenuData menuDataParent;
	
	@XmlElement(name = "Level", namespace = "")
    private Integer level;
	
	@XmlElement(name = "Order", namespace = "")
    private Integer order;
	
	@XmlElement(name = "Tree", namespace = "")
    private String tree;
	
	@XmlElement(name = "Function", namespace = "")
    private String function;
	
	@XmlElement(name = "ViewType", namespace = "")
    private String viewType;
	
	@XmlElement(name = "Status", namespace = "")
	private Integer status;
	
	@XmlElement(name = "Remarks", namespace = "")
	private String remarks;
	
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

	public MenuData() {}

	public MenuData(Menu menu) {
		if(menu != null){
			this.id = menu.getId();
			this.code = menu.getCode();
			this.name = menu.getName();
			if(menu.getMenuParent() != null)
				this.menuDataParent = new MenuData(menu.getMenuParent());
			this.level = menu.getLevel();
			this.order = menu.getOrder();
			this.tree = menu.getTree();
			this.function = menu.getFunction();
			this.viewType = menu.getViewType();
			this.status = menu.getStatus();
			this.remarks = menu.getRemarks();
			this.createdBy = menu.getCreatedBy();
			this.createdIP = menu.getCreatedIP();
			this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(menu.getCreatedTime());
			this.createdPlatform = menu.getCreatedPlatform();
			this.modifiedBy = menu.getModifiedBy();
			this.modifiedIP = menu.getModifiedIP();
			if(menu.getModifiedTime() != null)
				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(menu.getModifiedTime());
			this.modifiedPlatform = menu.getModifiedPlatform();
		}		
	}

	public MenuData(Integer id, String code, String name, Menu menuParent,
			Integer level, Integer order, String tree, 
			String function, String viewType, Integer status,
			String remarks, String createdBy, String createdIP,
			Date createdTime, String createdPlatform,
			String modifiedBy, String modifiedIP, 
			Date modifiedTime, String modifiedPlatform) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.menuDataParent = new MenuData(menuParent);
		this.level = level;
		this.order = order;
		this.tree = tree;
		this.function = function;
		this.viewType = viewType;
		this.status = status;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdIP = createdIP;
		this.createdTime = ParameterConstant.FORMAT_DEFAULT.format(createdTime);
		this.createdPlatform = createdPlatform;
		this.modifiedBy = modifiedBy;
		this.modifiedIP = modifiedIP;
		if(modifiedTime != null)
			this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.format(modifiedTime);
		this.modifiedPlatform = modifiedPlatform;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuData getMenuParent() {
		return menuDataParent;
	}

	public void setMenuParent(MenuData menuDataParent) {
		this.menuDataParent = menuDataParent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Override
	public String toString() {
		return "MenuData [id=" + id + ", code=" + code + ", name=" + name
				+ ", menuParent=" + menuDataParent + ", level=" + level
				+ ", order=" + order + ", tree=" + tree + ", function=" + function
				+ ", viewType=" + viewType + ", status=" + status 
				+ ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime + ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP
				+ ", modifiedTime=" + modifiedTime + ", modifiedPlatform=" + modifiedPlatform + "]";
	}

}
