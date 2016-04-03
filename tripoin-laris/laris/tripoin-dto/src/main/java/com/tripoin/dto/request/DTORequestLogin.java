package com.tripoin.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 4/3/2016 : 2:37 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DTORequestLogin {

    @SerializedName("viewType")
    String viewType;

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
