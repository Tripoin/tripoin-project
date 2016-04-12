package com.tripoin.dto.app.bca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class LanguageErrorMessage {

	@JsonProperty("Indonesian")
	String indonesian;
	
	@JsonProperty("English")
	String english;

	public String getIndonesian() {
		return indonesian;
	}

	public void setIndonesian(String indonesian) {
		this.indonesian = indonesian;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	@Override
	public String toString() {
		return "LanguageErrorMessage [Indonesian=" + indonesian + ", English="
				+ english + "]";
	}

}
