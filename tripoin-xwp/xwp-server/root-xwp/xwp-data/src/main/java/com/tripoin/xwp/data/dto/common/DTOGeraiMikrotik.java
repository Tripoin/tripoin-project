package com.tripoin.xwp.data.dto.common;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public class DTOGeraiMikrotik implements Serializable {

    private static final long serialVersionUID = 7893638769422148020L;

    private BigInteger id;

    private String userName;

    private String password;

    private String ipAddress;

    private String description;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DTOGeraiMikrotik{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
