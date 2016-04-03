package com.tripoin.dto.response;

import com.google.gson.annotations.SerializedName;
import com.tripoin.dto.app.DTOMenu;

/**
 * Created on 4/3/2016 : 2:40 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DTOResponseLogin {

    @SerializedName("menu")
    DTOMenu menu;

    public DTOMenu getMenu() {
        return menu;
    }

    public void setMenu(DTOMenu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "DTOResponseLogin{" +
                "menu=" + menu +
                '}';
    }
}
