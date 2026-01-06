package com.PullUp.DriverService.model.dto;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.DriverService.model.DriverLocation;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.model.RideDriver;

public class DriverDetailsResponseDTO {
	private UUID driverID;
	private String driverName;
	private String driverPhoneNumber;
	private DriverStatus status;
	private Instant createdAt;
	private DriverLocation recentLocation;
	
	public DriverDetailsResponseDTO(UUID driverID, String driverName, String driverPhoneNumber, DriverStatus status,
			Instant createdAt, DriverLocation recentLocation) {
		super();
		this.driverID = driverID;
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.status = status;
		this.createdAt = createdAt;
		this.recentLocation = recentLocation;
	}
	
	public DriverDetailsResponseDTO(RideDriver req){
		this.createdAt=req.getCreatedAt();
		this.driverID=req.getDriverID();
		this.driverName=req.getDriverName();
		this.driverPhoneNumber=req.getDriverPhoneNumber();
		this.recentLocation=req.getRecentLocation();
		this.status=req.getStatus();
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

	public DriverLocation getRecentLocation() {
		return recentLocation;
	}

	public void setRecentLocation(DriverLocation recentLocation) {
		this.recentLocation = recentLocation;
	}
	
	
	
}
