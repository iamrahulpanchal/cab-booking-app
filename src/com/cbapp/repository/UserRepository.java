package com.cbapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cbapp.entity.UserEntity;

public class UserRepository {
	List<UserEntity> users;
	
	public UserRepository() {
		users = new ArrayList<>();
	}
	
	public Optional<UserEntity> findByUsername(String username) {
		for(UserEntity userEntity: users) {
			if(userEntity.getUsername() == username) {
				return Optional.of(userEntity);
			}
		}
		
		return Optional.empty();
	}
	
	public Optional<List<UserEntity>> getAllUsers(){
		return Optional.of(users);
	}
	
	public void addUser(UserEntity userEntity) {
		users.add(userEntity);
	}
}
