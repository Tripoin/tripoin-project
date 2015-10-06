package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class UserTransferObject extends GeneralTransferObject {

	private List<UserData> userDatas;

	public List<UserData> getUserDatas() {
		return userDatas;
	}

	public void setUserDatas(List<UserData> userDatas) {
		this.userDatas = userDatas;
	}

	@Override
	public String toString() {
		return "UserDTO [userDatas=" + userDatas + "]";
	}

}
