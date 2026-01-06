package com.PullUp.RiderService.model.dto;

import java.util.UUID;

import com.PullUp.RiderService.model.GeoLocation;

public class RideCreationRequestDTO {
	private UUID riderId;
	
	private GeoLocation startLocation;
	private GeoLocation endLocation;
	
	public RideCreationRequestDTO(UUID riderId, GeoLocation startLocation, GeoLocation endLocation) {
		super();
		this.riderId = riderId;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
	}
	public UUID getRiderId() {
		return riderId;
	}
	public void setRiderId(UUID riderId) {
		this.riderId = riderId;
	}
	public GeoLocation getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(GeoLocation startLocation) {
		this.startLocation = startLocation;
	}
	public GeoLocation getEndLocation() {
		return endLocation;
	}
	public void setEndLocation(GeoLocation endLocation) {
		this.endLocation = endLocation;
	}
}
