package com.cbapp.entity;

public class VehicleEntity {
	private String modelName;
	private String vehicleNumber;
	
	public VehicleEntity(String modelName, String vehicleNumber) {
		super();
		this.modelName = modelName;
		this.vehicleNumber = vehicleNumber;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
}
