package com.PullUp.DriverService.model;

import java.time.Instant;

import jakarta.persistence.Embeddable;

@Embeddable
public class DriverLocation {
	private double latitude;
	
	private double longitude;
	
	private Instant updatedAt;
	
	public DriverLocation(double latitiude, double longitude, Instant updatedAt) {
		super();
		this.latitude = latitiude;
		this.longitude = longitude;
		this.updatedAt = updatedAt;
	}

	public DriverLocation() {
		super();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitiude) {
		this.latitude = latitiude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
}
