package com.cbapp.entity;

public class UserEntity {
	private String username;
	private Character gender;
	private Integer age;
	private String contact;
	private LocationEntity location;

	public UserEntity(String username, Character gender, Integer age, String contact, LocationEntity location) {
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
	
	public LocationEntity getLocation() {
		return location;
	}
	
	public void setLocation(LocationEntity location) {
		this.location = location;
	}

}
