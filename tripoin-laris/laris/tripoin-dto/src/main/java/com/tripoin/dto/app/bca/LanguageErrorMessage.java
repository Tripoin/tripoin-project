package com.tripoin.dto.app.bca;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class LanguageErrorMessage {

	String Indonesian;
	String English;

	public String getIndonesian() {
		return Indonesian;
	}

	public void setIndonesian(String indonesian) {
		Indonesian = indonesian;
	}

	public String getEnglish() {
		return English;
	}

	public void setEnglish(String english) {
		English = english;
	}

	@Override
	public String toString() {
		return "LanguageErrorMessage [Indonesian=" + Indonesian + ", English="
				+ English + "]";
	}

}
