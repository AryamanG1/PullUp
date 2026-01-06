package com.PullUp.RiderService.model.dto;

import com.PullUp.RiderService.model.Rider;

public class RiderCreationResponseDTO {
	private String riderName;
	private String riderPhone;
	public RiderCreationResponseDTO(String riderName, String riderPhone) {
		super();
		this.riderName = riderName;
		this.riderPhone = riderPhone;
	}
	
	public RiderCreationResponseDTO(Rider rider){
		this.riderName = rider.getRiderName();
		this.riderPhone = rider.getRiderPhoneNumber();
	}
	
	public String getRiderName() {
		return riderName;
	}
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	public String getRiderPhone() {
		return riderPhone;
	}
	public void setRiderPhone(String riderPhone) {
		this.riderPhone = riderPhone;
	}
	
	
}
