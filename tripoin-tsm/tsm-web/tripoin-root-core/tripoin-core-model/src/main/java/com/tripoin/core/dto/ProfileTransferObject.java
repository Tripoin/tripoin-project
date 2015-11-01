package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class ProfileTransferObject extends GeneralTransferObject {
	
	private List<ProfileData> profileDatas;

	public List<ProfileData> getProfileDatas() {
		return profileDatas;
	}

	public void setProfileDatas(List<ProfileData> profileDatas) {
		this.profileDatas = profileDatas;
	}

	@Override
	public String toString() {
		return "ProfileTransferObject [profileDatas=" + profileDatas + "]";
	}

}
