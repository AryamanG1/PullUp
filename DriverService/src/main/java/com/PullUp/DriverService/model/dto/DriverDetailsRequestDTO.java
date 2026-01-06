package com.PullUp.DriverService.model.dto;

import java.util.UUID;

public class DriverDetailsRequestDTO {
	private UUID driverId;

	public DriverDetailsRequestDTO(UUID driverId) {
		super();
		this.driverId = driverId;
	}

	public UUID getDriverId() {
		return driverId;
	}

	public void setDriverId(UUID driverId) {
		this.driverId = driverId;
	}
		
}
