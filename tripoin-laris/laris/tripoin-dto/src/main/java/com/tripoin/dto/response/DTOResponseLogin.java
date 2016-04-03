package com.tripoin.dto.response;

import com.google.gson.annotations.SerializedName;
import com.tripoin.dto.app.DTOMenu;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.app.UserData;

import java.util.List;

/**
 * Created on 4/3/2016 : 2:40 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DTOResponseLogin extends GeneralTransferObject{

    @SerializedName("menu")
    List<DTOMenu> dtoMenus;

    @SerializedName("userData")
    UserData userData;

    public List<DTOMenu> getDtoMenus() {
        return dtoMenus;
    }

    public void setDtoMenus(List<DTOMenu> dtoMenus) {
        this.dtoMenus = dtoMenus;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "DTOResponseLogin{" +
                "menu=" + dtoMenus +
                '}';
    }
}
