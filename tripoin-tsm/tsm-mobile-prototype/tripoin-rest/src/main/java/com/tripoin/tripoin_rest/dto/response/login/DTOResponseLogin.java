package com.tripoin.tripoin_rest.dto.response.login;

import com.google.gson.annotations.SerializedName;
import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.dto.response.DTOBaseResponse;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;

/**
 * Created on 10/2/2015 : 4:03 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Parcel
public class DTOResponseLogin extends DTOBaseResponse {

    @SerializedName(ApplicationConstant.Rest.DTO.Response.Login.USER_DATAS)
//    @ParcelPropertyConverter(UserDataListParcelConverter.class)
    private ArrayList<UserData> userDatas;

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
