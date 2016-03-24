package com.tripoin.core.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tripoin.core.pojo.Menu;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MenuData")
public class MenuData extends AGeneralAuditTrailData {

	@XmlElement(name = "ID", namespace = "")
	private Integer id;
	
	@XmlElement(name = "Code", namespace = "")
    private String code;
	
	@XmlElement(name = "Name", namespace = "")
    private String name;
	
	@XmlElement(name = "MenuDataParent", namespace = "")
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

	public MenuData() {}

	public MenuData(Menu menu) {
		super(menu);
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
		}		
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

	@Override
	public String toString() {
		return "MenuData [id=" + id + ", code=" + code + ", name=" + name + ", menuDataParent=" + menuDataParent
				+ ", level=" + level + ", order=" + order + ", tree=" + tree + ", function=" + function + ", viewType="
				+ viewType + "]";
	}

}
