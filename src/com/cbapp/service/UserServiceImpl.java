package com.cbapp.service;

import java.util.List;
import java.util.Optional;

import com.cbapp.dto.LocationDTO;
import com.cbapp.dto.UserDTO;
import com.cbapp.entity.LocationEntity;
import com.cbapp.entity.UserEntity;
import com.cbapp.exception.CabBookingException;
import com.cbapp.repository.UserRepository;
import com.cbapp.validation.UserValidation;

public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository = new UserRepository();
	private final UserValidation userValidation = new UserValidation();

	@Override
	public void addUser(UserDTO userDTO) throws CabBookingException {
		
		// Check for duplicate user with the same username
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userDTO.getUsername());
		if(optionalUserEntity.isPresent()) {
			throw new CabBookingException("User with the same username already exists...");
		}

		// Validating Input Fields
		userValidation.validateUser(userDTO);
		
		// Converting DTO to Entity & saving user to the Database/DS
		UserEntity userEntity = new UserEntity(
			userDTO.getUsername(),
			userDTO.getGender(),
			userDTO.getAge(),
			userDTO.getContact(),
			new LocationEntity(userDTO.getLocation().getxCoordinate(), userDTO.getLocation().getyCoordinate())
		);

		userRepository.addUser(userEntity);
		
		String response = "User " + userDTO.getUsername() + " has been Registered Successfully!!!";
		System.out.println(response);
	}
	
	@Override
	public void updateUserContact(String username, String contact) throws CabBookingException {
		UserEntity userEntity = getUserByUsername(username);
		
		userValidation.validateContact(contact);
		
		userEntity.setContact(contact);
		
		String response = "Contact details has been updated successfully for " + username + ".";
		System.out.println(response);
	}

	public void updateUserLocation(String username, LocationDTO locationDTO) throws CabBookingException {
		UserEntity userEntity = getUserByUsername(username);
		
		userValidation.validateLocation(locationDTO);

		LocationEntity locationEntity = userEntity.getLocation();
		locationEntity.setxCoordinate(locationDTO.getxCoordinate());
		locationEntity.setyCoordinate(locationDTO.getyCoordinate());
		
		userEntity.setLocation(locationEntity);
		
		String response = "Location has been updated successfully for " + username + ".";
		System.out.println(response);
	}
	
	public UserEntity getUserByUsername(String username) throws CabBookingException {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
		UserEntity userEntity = optionalUserEntity.orElseThrow(() -> new CabBookingException("User with " + username + " username does not exists."));

		return userEntity;
	}
	
	public List<UserEntity> getAllUsers() throws CabBookingException {
		Optional<List<UserEntity>> optionalUsersList = userRepository.getAllUsers();
		List<UserEntity> usersList = optionalUsersList.orElseThrow(() -> new CabBookingException("No Users present in the system."));
		return usersList;
	}
}
