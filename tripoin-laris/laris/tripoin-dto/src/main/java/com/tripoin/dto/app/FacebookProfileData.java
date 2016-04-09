package com.tripoin.dto.app;

import java.util.Arrays;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class FacebookProfileData {

	private String id;
	private String name;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private byte[] urlPhoto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(byte[] urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	@Override
	public String toString() {
		return "FacebookProfileData [id=" + id + ", name=" + name
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", email=" + email + ", urlPhoto="
				+ Arrays.toString(urlPhoto) + "]";
	}

}