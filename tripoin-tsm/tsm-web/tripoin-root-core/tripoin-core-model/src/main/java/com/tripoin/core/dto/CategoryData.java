package com.tripoin.core.dto;

import com.tripoin.core.pojo.Category;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class CategoryData {

	private Integer id;
    private String name;
    
    public CategoryData() {}    
    
    public CategoryData(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
	}   
    
    public CategoryData(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "CategoryData [id=" + id + ", name=" + name + "]";
	}
	
}
