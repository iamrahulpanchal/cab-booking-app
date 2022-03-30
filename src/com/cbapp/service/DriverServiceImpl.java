package com.cbapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cbapp.dto.DriverDTO;
import com.cbapp.dto.LocationDTO;
import com.cbapp.entity.DriverEntity;
import com.cbapp.entity.LocationEntity;
import com.cbapp.entity.VehicleEntity;
import com.cbapp.exception.CabBookingException;
import com.cbapp.repository.DriverRepository;
import com.cbapp.validation.DriverValidation;
import com.cbapp.validation.UserValidation;

public class DriverServiceImpl implements DriverService {
	
	private static final DriverRepository driverRepository = new DriverRepository();
	private static final UserValidation userValidation = new UserValidation();
	private static final DriverValidation driverValidation = new DriverValidation();

	@Override
	public void addDriver(DriverDTO driverDTO) throws CabBookingException {
		// Check for duplicate driver with the same username
		Optional<DriverEntity> optionaldriverEntity = driverRepository.findByUsername(driverDTO.getUsername());
		if(optionaldriverEntity.isPresent()) {
			throw new CabBookingException("User with the same username already exists...");
		}

		// Validating Input Fields
		userValidation.validateUser(driverDTO);
		driverValidation.validateDriver(driverDTO);
		
		// Converting DTO to Entity & saving user to the Database/DS
		DriverEntity driverEntity = new DriverEntity(
			driverDTO.getUsername(),
			driverDTO.getGender(),
			driverDTO.getAge(),
			driverDTO.getContact(),
			new LocationEntity(driverDTO.getLocation().getxCoordinate(), driverDTO.getLocation().getyCoordinate()),
			new VehicleEntity(driverDTO.getVehicle().getModelName(), driverDTO.getVehicle().getVehicleNumber()),
			driverDTO.getAvailable(),
			driverDTO.getTotalEarnings()
		);

		driverRepository.addDriver(driverEntity);
		
		String response = "Driver " + driverDTO.getUsername() + " has been Registered Successfully!!!";
		System.out.println(response);
	}

	@Override
	public void updateDriverLocation(String username, LocationDTO locationDTO) throws CabBookingException {
		DriverEntity driverEntity = getDriverByUsername(username);
		
		userValidation.validateLocation(locationDTO);
		
		LocationEntity locationEntity = driverEntity.getLocation();
		locationEntity.setxCoordinate(locationDTO.getxCoordinate());
		locationEntity.setyCoordinate(locationDTO.getyCoordinate());
		
		driverEntity.setLocation(locationEntity);
		
		String response = "Location has been updated sucessfully for " + username + ".";
		System.out.println(response);
	}
	
	@Override
	public void changeDriverStatus(String username, Boolean available) throws CabBookingException {
		DriverEntity driverEntity = getDriverByUsername(username);
		
		driverEntity.setAvailable(available);
		
		String response = "Availability Status has been set to " + driverEntity.getAvailable() + " for " + driverEntity.getUsername();
		System.out.println(response);
	}

	public List<String> findRides(String username, LocationDTO source, LocationDTO destination) throws CabBookingException {
		List<String> availableDriversName = new ArrayList<>();
		List<DriverEntity> availableDriversList = getAvailableDrivers();
		
		if(availableDriversList.isEmpty()) {
			return availableDriversName;
		}
		
		List<DriverEntity> filteredDriversList = new ArrayList<>();
		for(DriverEntity driverEntity: availableDriversList) {
			Integer xCordDiff = source.getxCoordinate() - driverEntity.getLocation().getxCoordinate();
			Integer yCordDiff = source.getyCoordinate() - driverEntity.getLocation().getyCoordinate();
			Double distance = Math.sqrt((xCordDiff * xCordDiff) - (yCordDiff * yCordDiff));
			
			if(distance <= 5.0) {
				filteredDriversList.add(driverEntity);
			}
		}
		
		if(filteredDriversList.isEmpty()) {
			return availableDriversName;
		}
		
		
		for(DriverEntity driverEntity: filteredDriversList) {
			availableDriversName.add(driverEntity.getUsername());
		}
		
		return availableDriversName;
	}
	
	public DriverEntity getDriverByUsername(String username) throws CabBookingException {
		Optional<DriverEntity> optionalDriverEntity = driverRepository.findByUsername(username);
		DriverEntity driverEntity = optionalDriverEntity.orElseThrow(() -> new CabBookingException("Driver with " + username + " username does not exists."));
		return driverEntity;
	}

	public List<DriverEntity> getAvailableDrivers() throws CabBookingException {
		Optional<List<DriverEntity>> optionalDriversList = driverRepository.getAvailableDrivers();
		List<DriverEntity> driversList = optionalDriversList.get();
		
		return driversList;
	}
	
	public List<DriverEntity> getAllDrivers() throws CabBookingException {
		Optional<List<DriverEntity>> optionalDriversList = driverRepository.getAllDrivers();
		List<DriverEntity> driversList = optionalDriversList.get();
		
		if(driversList.isEmpty()) {
			throw new CabBookingException("No Drivers Present in the system.");
		}
		
		return driversList;
	}

	public void chooseRide(String userName, String driverName, List<String> driversList, LocationDTO source, LocationDTO destination) throws CabBookingException {
		
		// Check if the driver is in the list of available drivers
		Boolean driverFound = false;
		for(String driver: driversList) {
			if(driver.equals(driverName)) {
				driverFound = true;
				break;
			}
		}
		
		if(driverFound == false) {
			throw new CabBookingException("Please choose the Driver from the list of Available Drivers");
		}
		
		// If the driver is valid, then set its availability to false
		changeDriverStatus(driverName, false);
		System.out.println();
		
		System.out.println("Ride Started...");
		
		// Calculate Bill
		calculateBill(driverName, source, destination);
		
		// Once the ride is complete, update driver's current location
		updateDriverLocation(driverName, destination);
	}

	private void calculateBill(String driverName, LocationDTO source, LocationDTO destination) throws CabBookingException {
		DriverEntity driverEntity = getDriverByUsername(driverName);
		
		Integer xCordDiff = source.getxCoordinate() - destination.getxCoordinate();
		Integer yCordDiff = source.getyCoordinate() - destination.getyCoordinate();
		Double distance = Math.sqrt((xCordDiff * xCordDiff) - (yCordDiff * yCordDiff));
		
		// For 1 Unit, Charge is $10
		Double bill = distance * 10.0;
		
		// Rounding off to 2 Decimals
		bill = Math.round(bill * 100.0) / 100.0;
		
		Double updatedEarnings = driverEntity.getTotalEarnings() + bill;
		driverEntity.setTotalEarnings(updatedEarnings);
		
		System.out.println("Ride Ended... Bill Amount: $" + driverEntity.getTotalEarnings());
		System.out.println();
	}

	public void getDriverEarnings() throws CabBookingException {
		List<DriverEntity> driversList = getAllDrivers();
		
		for(DriverEntity driver: driversList) {
			String earnings = driver.getUsername() + " earned $" + driver.getTotalEarnings();
			System.out.println(earnings);
		}
	}
}
