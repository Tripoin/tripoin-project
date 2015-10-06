package com.tripoin.core.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tripoin.core.pojo.Category;
import com.tripoin.core.pojo.Product;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProductData {
	
	private Integer id;
    private String productName;
    private BigDecimal price;
    private Integer stockCount; 
    private List<CategoryData> categoryDatas;
    private AvailabilityData availabilityData;
    
    public ProductData() {}
    
    public ProductData(Product product) {
		super();
		this.id = product.getId();
		this.productName = product.getProductName();
		this.price = product.getPrice();
		this.stockCount = product.getStockCount();
		this.categoryDatas = new ArrayList<CategoryData>();
		for(Category category : product.getCategories())
			this.categoryDatas.add(new CategoryData(category));
		this.availabilityData = new AvailabilityData(product.getAvailability());
	}
    
    public ProductData(int id, String productName, BigDecimal price,
			int stockCount, List<CategoryData> categoryDatas,
			AvailabilityData availabilityData) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.stockCount = stockCount;
		this.categoryDatas = categoryDatas;
		this.availabilityData = availabilityData;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

	public List<CategoryData> getCategoryDatas() {
		return categoryDatas;
	}

	public void setCategoryDatas(List<CategoryData> categoryDatas) {
		this.categoryDatas = categoryDatas;
	}

	public AvailabilityData getAvailabilityData() {
		return availabilityData;
	}

	public void setAvailabilityData(AvailabilityData availabilityData) {
		this.availabilityData = availabilityData;
	}

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", productName=" + productName
				+ ", price=" + price + ", stockCount=" + stockCount
				+ ", categoryDatas=" + categoryDatas + ", availabilityData="
				+ availabilityData + "]";
	}    
	
}
