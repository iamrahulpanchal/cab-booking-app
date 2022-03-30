package com.cbapp.entity;

public class DriverEntity extends UserEntity {
	private VehicleEntity vehicle;
	private Boolean available;
	private Double totalEarnings;
	
	public DriverEntity(String username, Character gender, Integer age, String contact, LocationEntity location,
			VehicleEntity vehicle, Boolean available, Double totalEarnings) {
		super(username, gender, age, contact, location);
		this.vehicle = vehicle;
		this.available = available;
		this.totalEarnings = totalEarnings;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
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
