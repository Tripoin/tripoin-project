package com.tripoin.core.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tripoin.core.dto.CategoryData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="trx_category")
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6317607513039565310L;
	private int id;
    private String name;
    private List<Product> products;

    public Category() {}
    
	public Category(CategoryData categoryData) {
		if(categoryData.getId() != null)
			this.id = categoryData.getId();
		this.name = categoryData.getName();
	}
	
	public Category(int id, String name, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Column(name="category_name", length=100)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories", cascade=CascadeType.ALL)
    public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
