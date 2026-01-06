package com.PullUp.RiderService.model.dto;

import java.util.UUID;

import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.model.RideStatus;

public class RideCancellationResponseDTO {
	private UUID rideId;
	private UUID riderId;
	private RideStatus rideStatus = RideStatus.CANCELLED;
	
	
	public RideCancellationResponseDTO(UUID rideId, UUID riderId, RideStatus rideStatus) {
		super();
		this.rideId = rideId;
		this.riderId = riderId;
		this.rideStatus = rideStatus;
	}
	
	
	public RideCancellationResponseDTO(Ride ride) {
		super();
		this.rideId = ride.getRideId();
		this.riderId = ride.getRiderId();
		this.rideStatus = ride.getStatus();
	}


	public UUID getRideId() {
		return rideId;
	}
	public UUID getRiderId() {
		return riderId;
	}
	public RideStatus getRideStatus() {
		return rideStatus;
	}
	
	
}
