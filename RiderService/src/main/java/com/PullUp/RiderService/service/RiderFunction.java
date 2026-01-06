package com.PullUp.RiderService.service;

import java.util.Optional;

import com.PullUp.RiderService.model.dto.ActiveRideRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationResponseDTO;
import com.PullUp.RiderService.model.dto.RideCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCreationResponseDTO;
import com.PullUp.RiderService.model.dto.RideDetailsRequestDTO;
import com.PullUp.RiderService.model.dto.RideDetailsResponseDTO;
import com.PullUp.RiderService.model.dto.RiderCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RiderCreationResponseDTO;


public interface RiderFunction {
	
	public RiderCreationResponseDTO createProfile(RiderCreationRequestDTO req);
	public RideCreationResponseDTO createRide(RideCreationRequestDTO req);
	public RideCancellationResponseDTO cancelRide(RideCancellationRequestDTO req);
	public RideDetailsResponseDTO getRide(RideDetailsRequestDTO req);
	public Optional<RideDetailsResponseDTO> getActiveRide(ActiveRideRequestDTO req);
	
}
