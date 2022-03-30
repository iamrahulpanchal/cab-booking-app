package com.cbapp.service;

import java.util.List;

import com.cbapp.dto.DriverDTO;
import com.cbapp.dto.LocationDTO;
import com.cbapp.entity.DriverEntity;
import com.cbapp.exception.CabBookingException;

public interface DriverService {
	void addDriver(DriverDTO driverDTO) throws CabBookingException;
	void updateDriverLocation(String username, LocationDTO locationDTO) throws CabBookingException;
	DriverEntity getDriverByUsername(String username) throws CabBookingException;
	List<DriverEntity> getAvailableDrivers() throws CabBookingException;
	void changeDriverStatus(String username, Boolean available) throws CabBookingException;
	List<String> findRides(String username, LocationDTO source, LocationDTO destination) throws CabBookingException;
	void chooseRide(String userName, String driverName, List<String> driversList, LocationDTO source, LocationDTO destination) throws CabBookingException;
	void getDriverEarnings() throws CabBookingException;
}
