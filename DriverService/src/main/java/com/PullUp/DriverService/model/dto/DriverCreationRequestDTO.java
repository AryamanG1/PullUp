package com.PullUp.DriverService.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class DriverCreationRequestDTO {
	@NotBlank
	private String driverName;
	
	@NotBlank
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid phone number"
    )
	private String driverPhoneNumber;
	

	public DriverCreationRequestDTO(@NotBlank String driverName,
			@NotBlank @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number") String driverPhoneNumber) {
		super();
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
	}
	
	

	public DriverCreationRequestDTO() {
		super();
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

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}
	
	
}
