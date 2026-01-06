package com.PullUp.MatchingService.producer;

import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.PullUp.MatchingService.event.DriverAssignmentProposedEvent;
import com.PullUp.MatchingService.event.RideRequestedEvent;

@Service
public class AssignmentKafkaService {
	private final KafkaTemplate<String,Object> kafkaTemplate;
	private static final Logger log =
            LoggerFactory.getLogger(AssignmentKafkaService.class);

	public AssignmentKafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishDriverAssignmentProposedEvent(UUID driverId , RideRequestedEvent inp){
		log.info("Publishing driver.assignment.proposed for rideId={}, driverId={}",
		         inp.rideId(), driverId);
		DriverAssignmentProposedEvent req = new DriverAssignmentProposedEvent(
																			 inp.rideId(),
																			 driverId,
																			 inp.pickupLatitude(),
																			 inp.pickupLongitude(),
																			 Instant.now()
																			 );
		kafkaTemplate.send("driver.assignment.proposed",driverId.toString(),req);
	}
}
