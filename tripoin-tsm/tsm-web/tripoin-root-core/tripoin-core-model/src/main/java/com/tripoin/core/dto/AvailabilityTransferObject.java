package com.tripoin.core.dto;

import java.util.List;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class AvailabilityTransferObject extends GeneralTransferObject {
	
	private List<AvailabilityData> availabilityDatas;

	public List<AvailabilityData> getAvailabilityDatas() {
		return availabilityDatas;
	}

	public void setAvailabilityDatas(List<AvailabilityData> availabilityDatas) {
		this.availabilityDatas = availabilityDatas;
	}

	@Override
	public String toString() {
		return "AvailabilityTransferObject [availabilityDatas="
				+ availabilityDatas + "]";
	}
	
}
