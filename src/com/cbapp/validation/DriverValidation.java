package com.cbapp.validation;

import com.cbapp.dto.DriverDTO;
import com.cbapp.dto.VehicleDTO;
import com.cbapp.exception.CabBookingException;

public class DriverValidation {

	public void validateDriver(DriverDTO driverDTO) throws CabBookingException {
		validateVehicle(driverDTO.getVehicle());
		validateAvailable(driverDTO.getAvailable());
		validateTotalEarnings(driverDTO.getTotalEarnings());
	}
	
	public void validateVehicle(VehicleDTO vehicleDTO) throws CabBookingException {
		if (!vehicleDTO.getModelName().getClass().getSimpleName().equals("String") || 
			!vehicleDTO.getVehicleNumber().getClass().getSimpleName().equals("String")) {
			throw new CabBookingException("Vehicle Details should be of String type");
		}
	}
	
	public void validateAvailable(Boolean available) throws CabBookingException {
		if(!available.getClass().getSimpleName().equals("Boolean")) {
			throw new CabBookingException("Availablity should be either true or false");
		}
	}
	
	public void validateTotalEarnings(Double earnings) throws CabBookingException {
		if(!earnings.getClass().getSimpleName().equals("Double")) {
			throw new CabBookingException("Earnings should be a Number");
		}
	}
}
