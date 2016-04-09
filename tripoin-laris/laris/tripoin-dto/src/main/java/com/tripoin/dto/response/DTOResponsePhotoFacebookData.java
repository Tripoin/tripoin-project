package com.tripoin.dto.response;

import com.tripoin.dto.app.PhotoFacebookData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class DTOResponsePhotoFacebookData {

	PhotoFacebookData data;

	public PhotoFacebookData getData() {
		return data;
	}

	public void setData(PhotoFacebookData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DTOResponsePhotoFacebookData [data=" + data + "]";
	}
	
}
