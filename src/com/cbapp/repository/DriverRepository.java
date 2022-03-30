package com.cbapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cbapp.entity.DriverEntity;

public class DriverRepository {
	List<DriverEntity> drivers;
	
	public DriverRepository() {
		drivers = new ArrayList<>();
	}
	
	public Optional<DriverEntity> findByUsername(String username) {
		for(DriverEntity driverEntity: drivers) {
			if(driverEntity.getUsername() == username) {
				return Optional.of(driverEntity);
			}
		}
		
		return Optional.empty();
	}

	public Optional<List<DriverEntity>> getAvailableDrivers(){
		List<DriverEntity> availableDrivers = new ArrayList<>();
		
		for(DriverEntity driverEntity: drivers) {
			if(driverEntity.getAvailable() == true) {
				availableDrivers.add(driverEntity);
			}
		}
		
		return Optional.of(availableDrivers);
	}
	
	public void addDriver(DriverEntity driverEntity) {
		drivers.add(driverEntity);
	}

	public Optional<List<DriverEntity>> getAllDrivers() {
		return Optional.of(drivers);
	}
}
