package com.tripoin.xwp.data.dto.request;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 2/27/2016.
 */
public class DTORequestMikrotik implements Serializable{

    private static final long serialVersionUID = -851875934904041882L;

    private String mikrotikName;

    public String getMikrotikName() {
        return mikrotikName;
    }

    public void setMikrotikName(String mikrotikName) {
        this.mikrotikName = mikrotikName;
    }
}
