package com.tripoin.tripoin_rest.dto.response;

import com.google.gson.annotations.SerializedName;
import com.tripoin.tripoin_common.constant.ApplicationConstant;

import org.parceler.Parcel;

/**
 * Created on 9/26/2015 : 6:27 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Parcel
public class DTOBaseResponse {

    @SerializedName(ApplicationConstant.Rest.DTO.Response.Base.RESPONSE_CODE)
    private int responseCode;

    @SerializedName(ApplicationConstant.Rest.DTO.Response.Base.RESPONSE_MESSAGE)
    private String responseMessage;

    @SerializedName(ApplicationConstant.Rest.DTO.Response.Base.RESPONSE_DESCRIPTION)
    private String responseDescription;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    @Override
    public String toString() {
        return "DTOBaseResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                '}';
    }
}
