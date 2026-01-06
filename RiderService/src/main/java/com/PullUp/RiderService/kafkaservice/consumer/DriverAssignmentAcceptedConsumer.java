package com.PullUp.RiderService.kafkaservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.PullUp.RiderService.events.DriverAssignmentAcceptedEvent;
import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.repository.RideRepository;

import jakarta.transaction.Transactional;

@Service
public class DriverAssignmentAcceptedConsumer {
	// This class will contain the service function where in we will consume the Driver Assignment Accepted
	//It will find the ride with the rideId in the contract and change it 
	private final RideRepository rideRepository;
	public DriverAssignmentAcceptedConsumer(RideRepository rideRepository) {
		super();
		this.rideRepository = rideRepository;
	}
	@KafkaListener(
			topics = "driver.assignment.accepted")
	@Transactional
	public void onDriverAssignmentAccepted(DriverAssignmentAcceptedEvent event){
		Ride ride = rideRepository.findById(event.rideId()).orElseThrow(() -> new IllegalArgumentException("Ride ID Invalid"));
		ride.markFoundDriver(event.driverId());
		rideRepository.save(ride);
	}
}
