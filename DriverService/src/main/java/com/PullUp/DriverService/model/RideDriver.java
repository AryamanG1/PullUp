package com.PullUp.DriverService.model;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.DriverService.model.dto.DriverCreationRequestDTO;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class RideDriver {
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID driverID;
	
	@NotBlank
	private String driverName;
	
	@NotBlank
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid phone number"
    )
	private String driverPhoneNumber;
	
	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	
	@Embedded
	private DriverLocation recentLocation;
	
	private Instant createdAt;

	public RideDriver(@NotBlank String driverName,
			@NotBlank @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number") String driverPhoneNumber) {
		super();
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
	}


	public RideDriver() {
	}
	
	public RideDriver(DriverCreationRequestDTO req){
		this.driverName = req.getDriverName();
		this.driverPhoneNumber = req.getDriverPhoneNumber();
		this.status = DriverStatus.OFFLINE;
	}
	
	public void markAvailable() {
	    if (status == DriverStatus.BUSY) {
	        throw new IllegalStateException("Busy driver cannot become available");
	    }
	    status = DriverStatus.AVAILABLE;
	}

	public void markBusy() {
	    if (status != DriverStatus.AVAILABLE) {
	        throw new IllegalStateException("Only available drivers can become busy");
	    }
	    status = DriverStatus.BUSY;
	}

	public void markOffline() {
	    if (status == DriverStatus.BUSY) {
	        throw new IllegalStateException("Busy driver cannot go offline");
	    }
	    status = DriverStatus.OFFLINE;
	}

	public UUID getDriverID(){
		return driverID;
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


	public void setDriverPhoneNumber(String riderPhoneNumber) {
		this.driverPhoneNumber = riderPhoneNumber;
	}


	public DriverStatus getStatus() {
		return status;
	}
	
	public DriverLocation getRecentLocation() {
		return recentLocation;
	}


	public void setRecentLocation(DriverLocation recentLocation) {
		this.recentLocation = recentLocation;
	}
	
	public void updateLocation(double latitude, double longitude) {
	    this.recentLocation = new DriverLocation(
	        latitude,
	        longitude,
	        Instant.now()
	    );
	}

	
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
	    this.createdAt = Instant.now();
	}
	
}
