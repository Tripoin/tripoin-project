package com.tripoin.core.pojo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name=ExchangeRate.TABLE_NAME)
public class ExchangeRate extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_ex_rate";

	private Integer id;
    private String code;
    private String name;
    private BigDecimal amount;
    private Currency currencySource;
    private Currency currencyTarget;

    public ExchangeRate() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ex_rate_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="ex_rate_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="ex_rate_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="ex_rate_amount")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name = "curency_source_id", nullable = false)
	public Currency getCurrencySource() {
		return currencySource;
	}

	public void setCurrencySource(Currency currencySource) {
		this.currencySource = currencySource;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name = "curency_target_id", nullable = false)
	public Currency getCurrencyTarget() {
		return currencyTarget;
	}

	public void setCurrencyTarget(Currency currencyTarget) {
		this.currencyTarget = currencyTarget;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "ExchangeRate [id=" + id + ", code=" + code + ", name=" + name
				+ ", amount=" + amount + ", currencySource.id=" + currencySource.getId()
				+ ", currencyTarget.id=" + currencyTarget.getId() + ", auditTrail=" + super.toString() + "]";
	}

}
