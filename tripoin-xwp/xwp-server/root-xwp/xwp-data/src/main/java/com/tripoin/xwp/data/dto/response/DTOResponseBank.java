package com.tripoin.xwp.data.dto.response;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public class DTOResponseBank implements Serializable{

    private static final long serialVersionUID = 5976318978238064545L;

    private BigInteger id;

    private String code;

    private String name;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
