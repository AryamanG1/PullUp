package com.PullUp.RiderService.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PullUp.RiderService.model.dto.ActiveRideRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationResponseDTO;
import com.PullUp.RiderService.model.dto.RideCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCreationResponseDTO;
import com.PullUp.RiderService.model.dto.RideDetailsRequestDTO;
import com.PullUp.RiderService.model.dto.RideDetailsResponseDTO;
import com.PullUp.RiderService.model.dto.RiderCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RiderCreationResponseDTO;
import com.PullUp.RiderService.response.ApiResponse;
import com.PullUp.RiderService.service.RiderFunction;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class RiderController {
	
	@Autowired
	private RiderFunction riderService;
	
	
	public RiderController(RiderFunction riderService) {
		super();
		this.riderService = riderService;
	}

	// We will be having 4 functions here 
	@PostMapping("/riders")
	public ResponseEntity<ApiResponse<?>> createNewRider(@RequestBody @Valid RiderCreationRequestDTO req ){
		try {
			RiderCreationResponseDTO res = riderService.createProfile(req);
			return ResponseEntity.ok(ApiResponse.success("Successfully Created new Rider Profile", res));
		}
		catch(ResponseStatusException e){
			return new ResponseEntity<>(ApiResponse.failure(e.getReason()),e.getStatusCode());
		}
	}
	
	@PostMapping("/rides")
	public ResponseEntity<ApiResponse<?>> createNewRide(@RequestBody @Valid RideCreationRequestDTO req ){
		try {
			RideCreationResponseDTO res = riderService.createRide(req);
			return ResponseEntity.ok(ApiResponse.success("Successfully Created new Ride Rquest", res));
		}
		catch(ResponseStatusException e){
			return new ResponseEntity<>(ApiResponse.failure(e.getReason()),e.getStatusCode());
		}
	}
	
	@PostMapping("/rides/cancel")
	public ResponseEntity<ApiResponse<?>> cancelRide(@RequestBody @Valid RideCancellationRequestDTO req ){
		try {
			RideCancellationResponseDTO res = riderService.cancelRide(req);
			return ResponseEntity.ok(ApiResponse.success("Successfully Cancelled Ride", res));
		}
		catch(ResponseStatusException e){
			return new ResponseEntity<>(ApiResponse.failure(e.getReason()),e.getStatusCode());
		}
	}
	
	@PostMapping("/rides/details")
	public ResponseEntity<ApiResponse<?>> getRideDetails(@RequestBody @Valid RideDetailsRequestDTO req ){
		try {
			RideDetailsResponseDTO res = riderService.getRide(req);
			return ResponseEntity.ok(ApiResponse.success("Successfully Fetched Ride Details", res));
		}
		catch(ResponseStatusException e){
			return new ResponseEntity<>(ApiResponse.failure(e.getReason()),e.getStatusCode());
		}
	}
	
	
	@GetMapping("/rides/active")
	public ResponseEntity<ApiResponse<?>> getActiveRideDetails(@RequestBody @Valid ActiveRideRequestDTO req ){
		try {
			Optional<RideDetailsResponseDTO> res = riderService.getActiveRide(req);
			if(res.isEmpty()){
				return ResponseEntity.ok(ApiResponse.success("No active ride found", null));
			}
			return ResponseEntity.ok(ApiResponse.success("Successfully Fetched Ride Avtive Details", res));
		}
		catch(ResponseStatusException e){
			return new ResponseEntity<>(ApiResponse.failure(e.getReason()),e.getStatusCode());
		}
	}
	
}
