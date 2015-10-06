package com.tripoin.core.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tripoin.core.dto.CategoryData;
import com.tripoin.core.dto.ProductData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name="trx_product")
public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;
	private int id;
    private String productName;
    private BigDecimal price;
    private int stockCount;
    private List<Category> categories;
    private Availability availability;    
    
    public Product() {}
    
	public Product(ProductData productData) {
		if(productData.getId() != null)
			this.id = productData.getId();
		this.productName = productData.getProductName();
		this.price = productData.getPrice();
		this.stockCount = productData.getStockCount();
		this.categories = new ArrayList<Category>();
		if(productData.getCategoryDatas() != null)
			for(CategoryData categoryData : productData.getCategoryDatas())
				this.categories.add(new Category(categoryData));
		if(productData.getAvailabilityData() != null)
			this.availability = new Availability(productData.getAvailabilityData());
	}
    
	public Product(int id, String productName, BigDecimal price,
			int stockCount, List<Category> categories, Availability availability) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.stockCount = stockCount;
		this.categories = categories;
		this.availability = availability;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Column(name="product_name", length=255)
    @NotNull
    @Size(min = 2, message = "Product name must have at least two characters")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

	@Column(name="product_price")
    @Min(0)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

	@Column(name="product_stock_count")
    @Min(value = 0, message = "Can't have negative amount in stock")
    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "trx_product_category", joinColumns = 
		{@JoinColumn(name = "product_id")}, 
		inverseJoinColumns = 
		{@JoinColumn(name = "category_id")})
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@ManyToOne
	@JoinColumn(name = "availability_id", nullable = false)
	public Availability getAvailability() {
		return availability;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName
				+ ", price=" + price + ", stockCount=" + stockCount
				+ ", categories=" + categories + ", availability="
				+ availability + "]";
	}     
	
}
