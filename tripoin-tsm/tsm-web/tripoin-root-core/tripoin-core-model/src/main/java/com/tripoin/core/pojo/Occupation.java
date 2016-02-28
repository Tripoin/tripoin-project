package com.tripoin.core.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tripoin.core.dto.OccupationData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=Occupation.TABLE_NAME)
public class Occupation extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_occupation";
    
	private Integer id;
    private String code;
    private String name;
    
    private List<Employee> employees;

    public Occupation() {}
    
	public Occupation(OccupationData occupationData) {
		super(occupationData);
		this.id = occupationData.getId();
		this.code = occupationData.getCode();
		this.name = occupationData.getName();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="occupation_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	@Column(name="occupation_code", unique=true, length=150)
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
	
	@OneToMany(mappedBy = "occupation", cascade=CascadeType.ALL)
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Occupation [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	
}
