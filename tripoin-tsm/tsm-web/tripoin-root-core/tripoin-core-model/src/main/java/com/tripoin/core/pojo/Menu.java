package com.tripoin.core.pojo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.MenuData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=Menu.TABLE_NAME)
public class Menu implements IBaseModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_menu";
    
	private Integer id;
    private String code;
    private String name;
    private Menu menuParent;
    private Integer level;
    private Integer order;
    private String tree;
    private String function;
    private String viewType;
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
    private List<Role> roles;

    public Menu() {}
    
	public Menu(MenuData menuData) {
		super();
		this.id = menuData.getId();
		this.code = menuData.getCode();
		this.name = menuData.getName();
		if(menuData.getMenuParent() != null)
			this.menuParent = new Menu(menuData.getMenuParent());
		this.level = menuData.getLevel();
		this.order = menuData.getOrder();
		this.tree = menuData.getTree();
		this.function = menuData.getFunction();
		this.viewType = menuData.getViewType();
		this.status = menuData.getStatus();
		this.remarks = menuData.getRemarks();
		this.createdBy = menuData.getCreatedBy();
		this.createdIP = menuData.getCreatedIP();
		try {
			if(menuData.getCreatedTime() != null)
				this.createdTime = ParameterConstant.FORMAT_DEFAULT.parse(menuData.getCreatedTime());
		} catch (ParseException e) {
			this.createdTime = new Date();
		}
		this.createdPlatform = menuData.getCreatedPlatform();
		this.modifiedBy = menuData.getModifiedBy();
		this.modifiedIP = menuData.getModifiedIP();
		try {
			if(menuData.getModifiedTime() != null)
				this.modifiedTime = ParameterConstant.FORMAT_DEFAULT.parse(menuData.getModifiedTime());
		} catch (ParseException e) {
			this.modifiedTime = new Date();
		}
		this.modifiedPlatform = menuData.getModifiedPlatform();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="menu_id")
    @NotNull
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="menu_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="menu_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @ManyToOne
    @JoinColumn(name = "menu_parent_id")
	public Menu getMenuParent() {
		return menuParent;
	}

	public void setMenuParent(Menu menuParent) {
		this.menuParent = menuParent;
	}

	@Column(name="menu_level")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name="menu_order")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name="menu_tree")
	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	@Column(name="menu_function")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Column(name="menu_view_type", length=50)
	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	@Column(name="menu_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="menu_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="menu_created_by", length=150)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="menu_created_ip", length=150)
	public String getCreatedIP() {
		return createdIP;
	}

	public void setCreatedIP(String createdIP) {
		this.createdIP = createdIP;
	}

	@Column(name="menu_created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="menu_created_platform")
	public String getCreatedPlatform() {
		return createdPlatform;
	}

	public void setCreatedPlatform(String createdPlatform) {
		this.createdPlatform = createdPlatform;
	}

	@Column(name="menu_modified_by", length=150)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="menu_modified_ip", length=150)
	public String getModifiedIP() {
		return modifiedIP;
	}

	public void setModifiedIP(String modifiedIP) {
		this.modifiedIP = modifiedIP;
	}

	@Column(name="menu_modified_time")
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="menu_modified_platform")
    public String getModifiedPlatform() {
		return modifiedPlatform;
	}

	public void setModifiedPlatform(String modifiedPlatform) {
		this.modifiedPlatform = modifiedPlatform;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "mst_menu_role", joinColumns = 
		{@JoinColumn(name = "menu_id")}, 
		inverseJoinColumns = 
		{@JoinColumn(name = "role_id")})
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}	

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", code=" + code + ", name=" + name
				+ ", menuParent=" + menuParent + ", level=" + level
				+ ", order=" + order + ", tree=" + tree + ", function=" + function
				+ ", viewType=" + viewType + ", status=" + status 
				+ ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdIP=" + createdIP + ", createdTime=" + createdTime
				+ ", createdPlatform=" + createdPlatform
				+ ", modifiedBy=" + modifiedBy + ", modifiedIP=" + modifiedIP
				+ ", modifiedTime=" + modifiedTime + ", modifiedPlatform=" + modifiedPlatform
				+ ", roles=" + roles + "]";
	}
	
}
