package com.tripoin.rest.dto.response.login;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.rest.dto.response.DTOBaseResponse;

/**
 * Created on 10/2/2015 : 4:03 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Parcel
public class DTOResponseLogin extends DTOBaseResponse {

    @SerializedName(ApplicationConstant.Rest.DTO.Response.Login.USER_DATAS)

    public ArrayList<UserData> userDatas;

    public ArrayList<UserData> getUserDatas() {
        return userDatas;
    }

    public void setUserDatas(ArrayList<UserData> userDatas) {
        this.userDatas = userDatas;
    }

    @Override
    public String toString() {
        return "DTOResponseLogin{" +
                "userDatas=" + userDatas +
                '}';
    }
}
