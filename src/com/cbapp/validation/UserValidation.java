package com.cbapp.validation;

import com.cbapp.dto.LocationDTO;
import com.cbapp.dto.UserDTO;
import com.cbapp.exception.CabBookingException;

public class UserValidation {
	public void validateUser(UserDTO userDTO) throws CabBookingException {
		validateUsername(userDTO.getUsername());
		validateAge(userDTO.getAge());
		validateContact(userDTO.getContact());
		validateGender(userDTO.getGender());
		validateLocation(userDTO.getLocation());
	}
	
	public void validateUsername(String username) throws CabBookingException {
		if(!username.getClass().getSimpleName().equals("String")) {
			throw new CabBookingException("Username should be of String type.");
		}
	}
	
	public void validateAge(Integer age) throws CabBookingException {
		if(!age.getClass().getSimpleName().equals("Integer")) {
			throw new CabBookingException("Age should be of Integer type");
		} else if (age < 16 && age > 100) {
			throw new CabBookingException("Please enter valid age. Age should be between 16 & 100");
		}
	}
	
	public void validateContact(String contact) throws CabBookingException {
		if(!contact.getClass().getSimpleName().equals("String")) {
			throw new CabBookingException("Contact should be of String type.");
		} else if (contact.length() != 10) {
			throw new CabBookingException("Please enter valid Contact Number. It should be of 10 digits.");
		} else if (contact.matches("[0-9]+") == false) {
			throw new CabBookingException("Please enter valid Contact Number.");
		}
	}
	
	public void validateGender(Character gender) throws CabBookingException {
		if(!gender.getClass().getSimpleName().equals("Character")) {
			throw new CabBookingException("Gender should be of Character type");
		} else if(gender != 'M' && gender != 'F') {
			throw new CabBookingException("Please enter valid Gender.");
		}
	}
	
	public void validateLocation(LocationDTO locationDTO) throws CabBookingException {
		if (!locationDTO.getxCoordinate().getClass().getSimpleName().equals("Integer") || 
			!locationDTO.getxCoordinate().getClass().getSimpleName().equals("Integer")) {
			throw new CabBookingException("Location should be of Integer type");
		}
	}
}
