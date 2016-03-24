package com.tripoin.core.pojo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=ProductDetail.TABLE_NAME)
public class ProductDetail extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "trx_product_detail";

	private Integer id;
    private String code;
    private String name;
    private BigDecimal price;
    private String discount;
    private Integer stock;
    private BigDecimal shippment;
    private Currency currency;
    private APIPost apiPost;

    public ProductDetail() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_detail_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="product_detail_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="product_detail_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="product_detail_price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name="product_detail_discount")
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Column(name="product_detail_stock")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Column(name="product_detail_shippment")
	public BigDecimal getShippment() {
		return shippment;
	}

	public void setShippment(BigDecimal shippment) {
		this.shippment = shippment;
	}

	@ManyToOne
    @JoinColumn(name = "currency_id")
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@ManyToOne
    @JoinColumn(name = "api_post_id")
	public APIPost getApiPost() {
		return apiPost;
	}

	public void setApiPost(APIPost apiPost) {
		this.apiPost = apiPost;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", code=" + code + ", name=" + name
				+ ", price=" + price + ", discount=" + discount + ", stock="
				+ stock + ", shippment=" + shippment + ", currency=" + currency
				+ ", apiPost=" + apiPost + ", auditTrail=" + super.toString() + "]";
	}
	
}
