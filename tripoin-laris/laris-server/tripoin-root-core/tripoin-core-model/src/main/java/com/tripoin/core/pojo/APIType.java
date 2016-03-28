package com.tripoin.core.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Entity
@Table(name=APIType.TABLE_NAME)
public class APIType extends AGeneralAuditTrail {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5575840012553613210L;	
    public static final String TABLE_NAME = "mst_api_type";

	private Integer id;
    private String code;
    private String name;
    private String identifier;
    private String secret;
    private String host;
    private String port;
    private String protocol;
    private String authorization;
    private String additional;

    public APIType() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="api_type_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="api_type_code", unique=true, length=150)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="api_type_name", length=255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="api_type_identifier")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Column(name="api_type_secret")
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(name="api_type_host")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name="api_type_port")
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Column(name="api_type_protocol")
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@Column(name="api_type_authorization")
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	@Column(name="api_type_additional")
	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	@Override
	public String tableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "APIType [id=" + id + ", code=" + code + ", name=" + name + ", identifier=" + identifier + ", secret="
				+ secret + ", host=" + host + ", port=" + port + ", protocol=" + protocol + ", authorization="
				+ authorization + ", additional=" + additional + "]";
	}

}
