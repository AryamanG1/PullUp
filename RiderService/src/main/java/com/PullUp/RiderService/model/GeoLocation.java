package com.PullUp.RiderService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {
	
	@Column(nullable = false)
	private double latitude;
	
	@Column(nullable = false)
	private double longitude;
	
	// Only JPA should be able to access
	protected GeoLocation(){}

	public GeoLocation(double latitude, double longitude) {
		validate(latitude,longitude);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void validate(double lat , double lng){
		if(lat < -90 || lat > 90){
			throw new IllegalArgumentException("Invalid Latitude");
		}
		if(lng < -90 || lng > 90){
			throw new IllegalArgumentException("Invalid Longitude");
		}
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
	
}
