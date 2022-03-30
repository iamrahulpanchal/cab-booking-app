package com.cbapp.service;

import java.util.List;

import com.cbapp.dto.LocationDTO;
import com.cbapp.dto.UserDTO;
import com.cbapp.entity.UserEntity;
import com.cbapp.exception.CabBookingException;

public interface UserService {
	void addUser(UserDTO userDTO) throws CabBookingException;
	void updateUserContact(String username, String contact) throws CabBookingException;
	void updateUserLocation(String username, LocationDTO locationDTO) throws CabBookingException;
	UserEntity getUserByUsername(String username) throws CabBookingException;
	List<UserEntity> getAllUsers() throws CabBookingException;
}
