package com.PullUp.RiderService.model.dto;

import java.util.UUID;

import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.model.RideStatus;

public class RideCreationResponseDTO {
	private UUID rideId;
	private UUID riderId;
	private RideStatus status;
	
	public RideCreationResponseDTO(UUID rideId, UUID riderId, RideStatus status) {
		this.rideId = rideId;
		this.riderId = riderId;
		this.status = status;
	}
	
	public RideCreationResponseDTO(Ride ride){
		this.rideId = ride.getRideId();
		this.riderId = ride.getRiderId();
		this.status = ride.getStatus();
	}
	public UUID getRideId() {
		return rideId;
	}

	public void setRideId(UUID rideId) {
		this.rideId = rideId;
	}
	public UUID getRiderId() {
		return riderId;
	}
	public void setRiderId(UUID riderId) {
		this.riderId = riderId;
	}
	public RideStatus getStatus() {
		return status;
	}
	public void setStatus(RideStatus status) {
		this.status = status;
	}
	
	
	
}
