package com.tripoin.core.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tripoin.core.dto.EmployeeData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=Employee.TABLE_NAME)
public class Employee extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_employee";
    
	private Integer id;
    private String code;
    private String nik;
    private Profile profile;
    private Occupation occupation;
    private Area area;
    private Employee employeeParent;

    private List<UserRoute> userRoutes;    

    public Employee() {}
    
	public Employee(EmployeeData employeeData) {
		super(employeeData);
		if(employeeData != null){
			this.id = employeeData.getId();
			this.code = employeeData.getCode();
			this.nik = employeeData.getNik();
			this.profile = new Profile(employeeData.getProfileData());
			this.occupation = new Occupation(employeeData.getOccupationData());
			this.area = new Area(employeeData.getAreaData());
			this.employeeParent = new Employee(employeeData.getEmployeeDataParent());	
		}
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

	@OneToOne(fetch = FetchType.LAZY)
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
    @JoinColumn(name = "area_id")
    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
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
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", code=" + code + ", nik=" + nik + ", profile.id=" + profile.getId() + ", occupation="
				+ occupation + ", area=" + area + ", employeeParent=" + employeeParent + "]";
	}
	
}
