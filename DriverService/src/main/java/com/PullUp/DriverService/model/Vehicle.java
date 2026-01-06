package com.PullUp.DriverService.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID vehicleID;
	
	@NotNull
	@Column(nullable=false,unique=true)	
	private UUID driverID;
	
	@NotBlank
	private String registrationNumber;
	
	@NotBlank
	private String make;
	
	@NotBlank
	private String model;
	
	@NotNull
	private Integer manufacturingYear;
	
	@NotBlank
	private String colour;
	
	@NotBlank
	private String vehicleType;
	
	@NotBlank
	private String insurancePolicyNumber;
	
	@Enumerated(EnumType.STRING)
	private VehicleStatus status;
	
	
	private Instant createdAt;
	
	public Vehicle(@NotBlank UUID driverID, @NotBlank String registrationNumber, @NotBlank String make,
			@NotBlank String model, @NotBlank Integer manufacturingYear, @NotBlank String colour,
			@NotBlank String vehicleType, @NotBlank String insurancePolicyNumber, VehicleStatus status) {
		super();
		this.driverID = driverID;
		this.registrationNumber = registrationNumber;
		this.make = make;
		this.model = model;
		this.manufacturingYear = manufacturingYear;
		this.colour = colour;
		this.vehicleType = vehicleType;
		this.insurancePolicyNumber = insurancePolicyNumber;
		this.status = status;
	}
	

	public Vehicle() {
		super();
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
	    this.createdAt = Instant.now();
	}


	public UUID getDriverID() {
		return driverID;
	}


	public void setDriverID(UUID driverID) {
		this.driverID = driverID;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public Integer getManufacturingYear() {
		return manufacturingYear;
	}


	public void setManufacturingYear(Integer manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}


	public String getVehicleType() {
		return vehicleType;
	}


	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}


	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}


	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}


	public VehicleStatus getStatus() {
		return status;
	}


	public void setStatus(VehicleStatus status) {
		this.status = status;
	}
	
}
