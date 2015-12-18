package com.tripoin.core.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tripoin.core.dto.RoleData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=Role.TABLE_NAME)
public class Role implements IBaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3911430996032351640L;
    public static final String TABLE_NAME = "sec_role";
	
	private Integer id;
	private String code;
	private List<User> users;
	private Integer status;
	private String remarks;
	private List<Menu> menus;
	
	public Role(){}

	public Role(RoleData roleData) {
		if(roleData != null){
			this.setId(roleData.getId());
			this.setCode(roleData.getCode());
			this.setStatus(roleData.getStatus());
			this.setRemarks(roleData.getRemarks());
		}
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="role_code", unique=true, length=50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Column(name="role_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="role_remarks", length=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    @ManyToMany(mappedBy = "roles", cascade=CascadeType.ALL)
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", code=" + code
				+ ", status=" + status + ", remarks=" + remarks + "]";
	}	
}
