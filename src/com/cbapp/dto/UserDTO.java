package com.cbapp.dto;

import java.util.Objects;

public class UserDTO {
	private String username;
	private Character gender;
	private Integer age;
	private String contact;
	private LocationDTO location;
	
	public UserDTO(String username, Character gender, Integer age, String contact, LocationDTO location) {
		super();
		this.username = username;
		this.gender = gender;
		this.age = age;
		this.contact = contact;
		this.location = location;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Character getGender() {
		return gender;
	}
	
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public LocationDTO getLocation() {
		return location;
	}
	public void setLocation(LocationDTO location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(username, other.username);
	}
}
