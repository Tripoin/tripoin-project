package com.tripoin.dto.response;

import com.google.gson.annotations.SerializedName;
import com.tripoin.dto.app.MenuData;
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
    List<MenuData> menuDatas;

    @SerializedName("userData")
    UserData userData;

	public List<MenuData> getMenuDatas() {
		return menuDatas;
	}

	public void setMenuDatas(List<MenuData> menuDatas) {
		this.menuDatas = menuDatas;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		return "DTOResponseLogin [menuDatas=" + menuDatas + ", userData=" + userData + "]";
	}
	
}
