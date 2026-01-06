package com.PullUp.MatchingService.consumer;

import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.PullUp.MatchingService.event.RideRequestedEvent;
import com.PullUp.MatchingService.producer.AssignmentKafkaService;
import com.PullUp.MatchingService.service.DriverGeoQueryService;

@Service
public class RideRequestedConsumer {
	
	private final AssignmentKafkaService assignmentProposedService;
	private final DriverGeoQueryService driverFindingService;
	
	
	

	public RideRequestedConsumer(AssignmentKafkaService assignmentProposedService,
			DriverGeoQueryService driverFindingService) {
		super();
		this.assignmentProposedService = assignmentProposedService;
		this.driverFindingService = driverFindingService;
	}

	@KafkaListener(
		topics = "ride.requested",
		groupId = "matching-service-group")
	public void onRideRequested( RideRequestedEvent req){
		System.out.println("Event captured succesfully" + req);
		String driverId = driverFindingService.findNearestDriver(req.pickupLatitude(),req.pickupLongitude(), 100000).orElseThrow(() -> new IllegalArgumentException("No drivers found in the area"));
		assignmentProposedService.publishDriverAssignmentProposedEvent(UUID.fromString(driverId), req);
		
	}

}
