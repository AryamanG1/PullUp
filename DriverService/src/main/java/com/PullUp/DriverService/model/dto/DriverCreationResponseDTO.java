package com.PullUp.DriverService.model.dto;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.model.RideDriver;

public class DriverCreationResponseDTO {
	private UUID driverID;
	private String driverName;
	private String driverPhoneNumber;
	private DriverStatus status;
	private Instant createdAt;
	
	
	public DriverCreationResponseDTO(UUID driverID, String driverName, String driverPhoneNumber, DriverStatus status,
			Instant createdAt) {
		super();
		this.driverID = driverID;
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	public DriverCreationResponseDTO(RideDriver driver){
		this.driverID=driver.getDriverID();
		this.driverName=driver.getDriverName();
		this.driverPhoneNumber=driver.getDriverPhoneNumber();
		this.status=driver.getStatus();
		this.createdAt=driver.getCreatedAt();
	}
	
	public DriverCreationResponseDTO() {
		super();
	}
	
	public UUID getDriverID() {
		return driverID;
	}
	public void setDriverID(UUID driverID) {
		this.driverID = driverID;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}
	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}
	public DriverStatus getStatus() {
		return status;
	}
	public void setStatus(DriverStatus status) {
		this.status = status;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
