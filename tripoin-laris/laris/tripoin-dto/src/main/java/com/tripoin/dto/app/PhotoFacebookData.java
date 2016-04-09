package com.tripoin.dto.app;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class PhotoFacebookData {

	String is_silhouette;
	String url;

	public String getIs_silhouette() {
		return is_silhouette;
	}

	public void setIs_silhouette(String is_silhouette) {
		this.is_silhouette = is_silhouette;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "PhotoFacebookData [is_silhouette=" + is_silhouette + ", url="
				+ url + "]";
	}

}
