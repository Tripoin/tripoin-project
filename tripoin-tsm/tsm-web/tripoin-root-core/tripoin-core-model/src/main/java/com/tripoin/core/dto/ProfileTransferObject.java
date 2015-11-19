package com.tripoin.core.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProfileData")
public class ProfileTransferObject extends GeneralTransferObject {
	
	@XmlElement(name = "ProfileDatas", namespace = "")
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
