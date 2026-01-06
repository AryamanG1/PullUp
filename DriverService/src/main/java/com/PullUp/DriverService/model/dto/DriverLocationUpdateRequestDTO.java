package com.PullUp.DriverService.model.dto;

import java.util.UUID;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class DriverLocationUpdateRequestDTO {

    @NotNull
    private UUID driverId;

    @NotNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    private Double latitude;

    @NotNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    private Double longitude;

	public DriverLocationUpdateRequestDTO(@NotNull UUID driverId,
			@NotNull @DecimalMin("-90.0") @DecimalMax("90.0") Double latitude,
			@NotNull @DecimalMin("-180.0") @DecimalMax("180.0") Double longitude) {
		super();
		this.driverId = driverId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	public DriverLocationUpdateRequestDTO() {
		super();
	}

	public UUID getDriverId() {
		return driverId;
	}

	public void setDriverId(UUID driverId) {
		this.driverId = driverId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
    
    
}
