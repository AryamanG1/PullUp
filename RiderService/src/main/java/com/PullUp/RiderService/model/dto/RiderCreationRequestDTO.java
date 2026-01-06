package com.PullUp.RiderService.model.dto;

import com.PullUp.RiderService.model.PaymentMethod;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RiderCreationRequestDTO {
	@NotBlank
	private String riderName;
	
	@NotBlank
	@Pattern(
			regexp = "^[6-9]\\d{9}$",
			message = "Invalid phone number"
	)
	private String riderPhoneNumber;
	
	@Email
	private String riderEmail;
	
	private PaymentMethod defaultPaymentMethod;

	
	
	public RiderCreationRequestDTO(@NotBlank String riderName,
			@NotBlank @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number") String riderPhoneNumber,
			@Email String riderEmail, PaymentMethod defaultPaymentMethod) {
		super();
		this.riderName = riderName;
		this.riderPhoneNumber = riderPhoneNumber;
		this.riderEmail = riderEmail;
		this.defaultPaymentMethod = defaultPaymentMethod;
	}
	

	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public String getRiderPhoneNumber() {
		return riderPhoneNumber;
	}
	public void setRiderPhoneNumber(String riderPhoneNumber) {
		this.riderPhoneNumber = riderPhoneNumber;
	}
	public String getRiderEmail() {
		return riderEmail;
	}
	public void setRiderEmail(String riderEmail) {
		this.riderEmail = riderEmail;
	}
	public PaymentMethod getDefaultPaymentMethod() {
		return defaultPaymentMethod;
	}
	public void setDefaultPaymentMethod(PaymentMethod defaultPaymentMethod) {
		this.defaultPaymentMethod = defaultPaymentMethod;
	}
	
}
