package com.tripoin.rest.dto.request.login;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created on 1/8/2016 : 3:21 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Parcel
public class DTORequestLogin {

    @SerializedName("userName")
    public String userName;

    @SerializedName("password")
    public String password;

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

    @Override
    public String toString() {
        return "DTORequestLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
