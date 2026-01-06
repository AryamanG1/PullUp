package com.PullUp.DriverService.kafkaservice;

import java.time.Instant;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.PullUp.DriverService.events.DriverAssignmentAcceptedEvent;
import com.PullUp.DriverService.events.DriverAssignmentProposedEvent;
import com.PullUp.DriverService.events.DriverAssignmentRejectedEvent;
import com.PullUp.DriverService.events.DriverLocationUpdatedEvent;
import com.PullUp.DriverService.events.DriverStatusChangedEvent;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.model.RideDriver;

@Service
public class DriverKafkaService {

	private final KafkaTemplate<String,Object> kafkaTemplate;

	public DriverKafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishDriverLocationUpdatedEvent(RideDriver driver){
		DriverLocationUpdatedEvent req = new DriverLocationUpdatedEvent(driver.getDriverID(),
																	   driver.getRecentLocation().getLatitude(),
																	   driver.getRecentLocation().getLongitude(),
																	   Instant.now(),
																	   driver.getStatus());
		kafkaTemplate.send("driver.location.updated",driver.getDriverID().toString(),req);
	}
	
	public void publishDriverStatusChangedEvent(RideDriver driver , DriverStatus oldStatus){
		DriverStatusChangedEvent req = new DriverStatusChangedEvent(driver.getDriverID(),oldStatus,driver.getStatus(),driver.getRecentLocation().getLatitude(),driver.getRecentLocation().getLongitude(),Instant.now());
		kafkaTemplate.send("driver.status.changed",driver.getDriverID().toString(),req);
	}
	
	public void publishDriverAssignmentAccepted(DriverAssignmentProposedEvent req){
		DriverAssignmentAcceptedEvent output = new DriverAssignmentAcceptedEvent(req.rideId(),req.driverId(),Instant.now());
		kafkaTemplate.send("driver.assignment.accepted",req.rideId().toString(),output);
	}
	public void publishDriverAssignmentRejected(DriverAssignmentProposedEvent req){
		DriverAssignmentRejectedEvent output = new DriverAssignmentRejectedEvent(req.rideId(),req.driverId(),"Some Reason",Instant.now());
		kafkaTemplate.send("driver.assignment.rejected",req.rideId().toString(),output);
	}
}
