package com.PullUp.RiderService.kafkaservice;

import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.PullUp.RiderService.events.RideCancelledEvent;
import com.PullUp.RiderService.events.RideRequestedEvent;
import com.PullUp.RiderService.model.CancelledBy;
import com.PullUp.RiderService.model.Ride;

@Service
public class RideKafkaService {
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public RideKafkaService(KafkaTemplate<String, Object> kafkaTemplate1) {
		super();
		this.kafkaTemplate = kafkaTemplate1;
	}

	public void publishRideRequestedEvent(Ride ride){
		RideRequestedEvent event = new RideRequestedEvent(ride.getRideId(),
														 ride.getRiderId(),
														 ride.getStartLocation().getLatitude(),
														 ride.getStartLocation().getLongitude(),
														 Instant.now());
		kafkaTemplate.send("ride.requested",ride.getRideId().toString(),event);
	}
	
	public void publishRideCancelledEvent(Ride ride){
		RideCancelledEvent event = new RideCancelledEvent(ride.getRideId(),ride.getRiderId(),CancelledBy.RIDER,"Some Reason",Instant.now());
		kafkaTemplate.send("ride.cancelled",ride.getRideId().toString(),event);
	}

}
