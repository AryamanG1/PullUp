package com.PullUp.DriverService.model.dto;

import java.util.UUID;

import com.PullUp.DriverService.model.DriverStatus;

public class DriverStatusChangeRequestDTO {
	private UUID driverId;
	private DriverStatus newStatus;
	
	public DriverStatusChangeRequestDTO(UUID driverID, DriverStatus newStatus) {
		super();
		this.driverId = driverID;
		this.newStatus = newStatus;
	}
	
	public UUID getDriverId() {
		return driverId;
	}
	public void setDriverId(UUID driverID) {
		this.driverId = driverID;
	}
	public DriverStatus getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(DriverStatus newStatus) {
		this.newStatus = newStatus;
	}
	
	
}	
