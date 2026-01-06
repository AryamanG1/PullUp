package com.PullUp.RiderService.model.dto;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.RiderService.model.GeoLocation;
import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.model.RideStatus;

public class RideDetailsResponseDTO {
	private UUID rideId;
	private UUID riderId;
	private UUID driverId;
	private GeoLocation startLocation;
	private GeoLocation endLocation;
	private Instant createdAt;
	private RideStatus status;
	
	public RideDetailsResponseDTO(UUID rideId, UUID riderId, UUID driverId, GeoLocation startLocation,
			GeoLocation endLocation, Instant createdAt, RideStatus status) {
		super();
		this.rideId = rideId;
		this.riderId = riderId;
		this.driverId = driverId;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.createdAt = createdAt;
		this.status = status;
	}
	
	public RideDetailsResponseDTO(Ride ride){
		this.rideId= ride.getRideId();
		this.riderId = ride.getRiderId();
		this.driverId = ride.getDriverId();
		this.startLocation = ride.getStartLocation();
		this.endLocation = ride.getEndLocation();
		this.createdAt = ride.getCreatedAt();
		this.status = ride.getStatus();
	}

	public UUID getRideId() {
		return rideId;
	}

	public UUID getRiderId() {
		return riderId;
	}

	public UUID getDriverId() {
		return driverId;
	}

	public GeoLocation getStartLocation() {
		return startLocation;
	}

	public GeoLocation getEndLocation() {
		return endLocation;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public RideStatus getStatus() {
		return status;
	}
	
	
	
	
}
