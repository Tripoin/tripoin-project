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
@XmlRootElement(name = "UserRouteTransferObject")
public class UserRouteTransferObject extends GeneralTransferObject {

	@XmlElement(name = "UserRouteDatas", namespace = "")
	private List<UserRouteData> userRouteDatas;

	public List<UserRouteData> getUserRouteDatas() {
		return userRouteDatas;
	}

	public void setUserRouteDatas(List<UserRouteData> userRouteDatas) {
		this.userRouteDatas = userRouteDatas;
	}

	@Override
	public String toString() {
		return "UserRouteTransferObject [userRouteDatas=" + userRouteDatas
				+ "]";
	}

}
