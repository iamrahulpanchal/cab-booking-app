package com.cbapp;

import java.util.List;

import com.cbapp.dto.DriverDTO;
import com.cbapp.dto.LocationDTO;
import com.cbapp.dto.UserDTO;
import com.cbapp.dto.VehicleDTO;
import com.cbapp.exception.CabBookingException;
import com.cbapp.service.DriverService;
import com.cbapp.service.DriverServiceImpl;
import com.cbapp.service.UserService;
import com.cbapp.service.UserServiceImpl;

public class Driver {

	private static final UserService userService = new UserServiceImpl();
	private static final DriverService driverService = new DriverServiceImpl();
	
	public static void main(String[] args) {
		
		// Setting up Users
		LocationDTO userLocationDTO1 = new LocationDTO(0, 0);
		UserDTO userDTO1 = new UserDTO("Rahul", 'M', 25, "8879666228", userLocationDTO1);
		UserDTO userDTO2 = new UserDTO("Ronak", 'M', 22, "8445096228", userLocationDTO1);
		UserDTO userDTO3 = new UserDTO("Aishwarya", 'F', 18, "1445096228", userLocationDTO1);

		try {
			// Adding Users
			userRegistration(userDTO1, userDTO2, userDTO3);

			// Updating User Contact Details
			updateUserContact(userDTO1);
			
			// Updating User Location
			updateUserLocation(userDTO1);
			
		} catch (CabBookingException cbe) {
			System.out.println("Error: " + cbe.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong. " + e.getMessage());
		}
		
		// Setting up Drivers
		LocationDTO driverLocationDTO1 = new LocationDTO(0, 0);
		VehicleDTO vehicleDTO1 = new VehicleDTO("Swift", "MH 03 1133");
		DriverDTO driverDTO1 = new DriverDTO("Prince", 'M', 30, "7031275618", driverLocationDTO1, vehicleDTO1, false, 0.0);
		DriverDTO driverDTO2 = new DriverDTO("Prem", 'M', 25, "7011275618", driverLocationDTO1, vehicleDTO1, true, 0.0);
		DriverDTO driverDTO3 = new DriverDTO("Rishab", 'M', 29, "7021275618", driverLocationDTO1, vehicleDTO1, true, 0.0);
		
		try {
			// Adding Drivers
			driverRegistration(driverDTO1, driverDTO2, driverDTO3);
			
			// Changing Driver Availability Status to False
			setAvailableToFalse(driverDTO2, driverDTO3);

			// Find Rides (No Drivers Available)
			findRidesNoDriversAvailable(userDTO1, userDTO2);

			// Changing Driver Availability Status to True
			setAvailableToTrue(driverDTO1, driverDTO2);

			// Find Rides (No Drivers Found. Since all drivers are 5 km far)
			findRidesDrivers5kmAway(userDTO1, userDTO2);

			// Updating Driver Location
			updateDriverLocation(driverDTO1, driverDTO2);
			
			// Find Rides & Choose Ride (Valid)
			findRidesAndChooseRide(userDTO1, driverDTO2);
			
			// All Driver Earnings
			allDriversEarnings();

		} catch (CabBookingException cbe) {
			System.out.println("Error: " + cbe.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong. " + e.getMessage());
		}
	}

	private static void userRegistration(UserDTO userDTO1, UserDTO userDTO2, UserDTO userDTO3) throws CabBookingException {
		System.out.println("User Registration:");
		System.out.println();
		userService.addUser(userDTO1);
		userService.addUser(userDTO2);
		userService.addUser(userDTO3);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void updateUserContact(UserDTO userDTO1) throws CabBookingException {
		System.out.println("Updating User Contact Details:");
		System.out.println();
		userDTO1.setContact("7022275658");
		userService.updateUserContact(userDTO1.getUsername(), userDTO1.getContact());
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void updateUserLocation(UserDTO userDTO1) throws CabBookingException {
		System.out.println("Updating User Location:");
		System.out.println();
		LocationDTO userLocation1 = userDTO1.getLocation();
		userLocation1.setxCoordinate(12);
		userLocation1.setyCoordinate(1);
		userDTO1.setLocation(userLocation1);
		userService.updateUserLocation(userDTO1.getUsername(), userDTO1.getLocation());
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void driverRegistration(DriverDTO driverDTO1, DriverDTO driverDTO2, DriverDTO driverDTO3) throws CabBookingException {
		System.out.println("Driver Registration:");
		System.out.println();
		driverService.addDriver(driverDTO1);
		driverService.addDriver(driverDTO2);
		driverService.addDriver(driverDTO3);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void setAvailableToFalse(DriverDTO driverDTO2, DriverDTO driverDTO3) throws CabBookingException {
		System.out.println("Changing Driver Availability Status:");
		System.out.println();
		driverService.changeDriverStatus(driverDTO2.getUsername(), false);
		driverService.changeDriverStatus(driverDTO3.getUsername(), false);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void findRidesNoDriversAvailable(UserDTO userDTO1, UserDTO userDTO2) throws CabBookingException {
		System.out.println("Find Rides (No Drivers Available):");
		System.out.println();
		LocationDTO source = userDTO2.getLocation();
		LocationDTO destination = new LocationDTO(18, 2);
		List<String> driversList = driverService.findRides(userDTO1.getUsername(), source, destination);
		if(driversList.isEmpty()) {
			System.out.println("None of the Drivers are Available");
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void setAvailableToTrue(DriverDTO driverDTO1, DriverDTO driverDTO2) throws CabBookingException {
		System.out.println("Changing Driver Availability Status:");
		System.out.println();
		driverService.changeDriverStatus(driverDTO1.getUsername(), true);
		driverService.changeDriverStatus(driverDTO2.getUsername(), true);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void findRidesDrivers5kmAway(UserDTO userDTO1, UserDTO userDTO2) throws CabBookingException {
		System.out.println("Find Rides (No Drivers Found. Since all drivers are 5 km far):");
		System.out.println();
		LocationDTO source = userDTO2.getLocation();
		LocationDTO destination = new LocationDTO(18, 2);
		List<String> availableDriversList = driverService.findRides(userDTO1.getUsername(), source, destination);
		if(availableDriversList.isEmpty()) {
			System.out.println("No Drivers Found. Since all the available drivers are more than 5km away from your location");
		}
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void updateDriverLocation(DriverDTO driverDTO1, DriverDTO driverDTO2) throws CabBookingException {
		System.out.println("Updating Driver Location:");
		System.out.println();
		
		LocationDTO driverLocation1 = driverDTO1.getLocation();
		driverLocation1.setxCoordinate(8);
		driverLocation1.setyCoordinate(0);
		driverDTO1.setLocation(driverLocation1);
		
		LocationDTO driverLocation2 = driverDTO2.getLocation();
		driverLocation2.setxCoordinate(10);
		driverLocation2.setyCoordinate(0);
		driverDTO2.setLocation(driverLocation2);
		
		driverService.updateDriverLocation(driverDTO1.getUsername(), driverDTO1.getLocation());
		driverService.updateDriverLocation(driverDTO2.getUsername(), driverDTO2.getLocation());
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void findRidesAndChooseRide(UserDTO userDTO1, DriverDTO driverDTO2) throws CabBookingException {
		System.out.println("Find Rides & Choose Ride (Valid):");
		System.out.println();
		LocationDTO source = userDTO1.getLocation();
		LocationDTO destination = new LocationDTO(18, 2);
		List<String> validDriversList = driverService.findRides(userDTO1.getUsername(), source, destination);
		
		System.out.println("Hey " + userDTO1.getUsername() + ", Please choose one of the drivers from the below list.");
		System.out.println();
		System.out.println("Available Drivers Name:");
		int i = 1;
		for(String driver: validDriversList) {
			System.out.println(i + ". " + driver);
			i++;
		}
		System.out.println();
		
		// Choose Ride
		driverService.chooseRide(userDTO1.getUsername(), driverDTO2.getUsername(), validDriversList, source, destination);
		userService.updateUserLocation(userDTO1.getUsername(), destination);
		System.out.println("-----------------------------------------------------------------");
	}
	
	private static void allDriversEarnings() throws CabBookingException {
		System.out.println("All Driver Earnings:");
		System.out.println();
		driverService.getDriverEarnings();
		System.out.println("-----------------------------------------------------------------");
	}
}
