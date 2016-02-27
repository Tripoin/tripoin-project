package com.tripoin.xwp.data.dto.common;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public class DTOBank implements Serializable{

    private static final long serialVersionUID = 5976318978238064545L;

    private String id;

    private String code;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
