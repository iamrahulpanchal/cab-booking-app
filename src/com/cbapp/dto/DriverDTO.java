package com.cbapp.dto;

public class DriverDTO extends UserDTO {
	private VehicleDTO vehicle;
	private Boolean available;
	private Double totalEarnings;

	public DriverDTO(String username, Character gender, Integer age, String contact, LocationDTO location,
			VehicleDTO vehicle, Boolean available, Double totalEarnings) {
		super(username, gender, age, contact, location);
		this.vehicle = vehicle;
		this.available = available;
		this.totalEarnings = totalEarnings;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Double getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(Double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

}
