package com.PullUp.RiderService.model;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.RiderService.model.dto.RiderCreationRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID riderId;
	
	@NotBlank
	private String riderName;
	
	 @NotBlank
	    @Pattern(
	        regexp = "^[6-9]\\d{9}$",
	        message = "Invalid phone number"
	    )
	private String riderPhoneNumber;
	
	@Email
	@Column(unique = true)
	private String riderEmail;
	
	private PaymentMethod defaultPaymentMethod;
	
	private Instant createdAt;
	
	private Boolean isEmailValid;
	private Boolean isPhoneValid;
		
	
	public Rider(@NotBlank String riderName,
			@NotBlank @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number") String riderPhoneNumber,
			@Email String riderEmail, PaymentMethod defaultPaymentMethod, Instant createdAt, Boolean isEmailValid,
			Boolean isPhoneValid) {
		this.riderName = riderName;
		this.riderPhoneNumber = riderPhoneNumber;
		this.riderEmail = riderEmail;
		this.defaultPaymentMethod = defaultPaymentMethod;
		this.createdAt = createdAt;
		this.isEmailValid = isEmailValid;
		this.isPhoneValid = isPhoneValid;
	}
	
	public Rider(){}
	
	public Rider(RiderCreationRequestDTO req){
		this.riderEmail = req.getRiderEmail();
		this.riderName = req.getRiderName();
		this.defaultPaymentMethod = req.getDefaultPaymentMethod();
		this.riderPhoneNumber = req.getRiderPhoneNumber();
	}
	
	
	public UUID getRiderId() {
		return riderId;
	}
	public void setRiderId(UUID riderId) {
		this.riderId = riderId;
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
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
	    this.createdAt = Instant.now();
	}
	
	public Boolean getIsEmailValid() {
		return isEmailValid;
	}
	public void setIsEmailValid(Boolean isEmailValid) {
		this.isEmailValid = isEmailValid;
	}
	public Boolean getIsPhoneValid() {
		return isPhoneValid;
	}
	public void setIsPhoneValid(Boolean isPhoneValid) {
		this.isPhoneValid = isPhoneValid;
	}
	
	
	
	
}
