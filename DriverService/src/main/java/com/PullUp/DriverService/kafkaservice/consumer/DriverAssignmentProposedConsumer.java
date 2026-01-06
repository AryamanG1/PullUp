package com.PullUp.DriverService.kafkaservice.consumer;

import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.PullUp.DriverService.events.DriverAssignmentProposedEvent;
import com.PullUp.DriverService.kafkaservice.DriverKafkaService;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.model.RideDriver;
import com.PullUp.DriverService.repository.DriverRepository;

@Service
public class DriverAssignmentProposedConsumer {
	
	private final DriverRepository driverRepository;
	private final DriverKafkaService kafkaService;

	public DriverAssignmentProposedConsumer(DriverRepository driverRepository, DriverKafkaService kafkaService) {
		super();
		this.driverRepository = driverRepository;
		this.kafkaService = kafkaService;
	}

	@KafkaListener(
			topics="driver.assignment.proposed")
	public void onDriverAssignmentProposed(@Payload DriverAssignmentProposedEvent req){
		// Step 1 --> We will first fetch the Driver with the driver Id
		RideDriver driver = driverRepository.findById(req.driverId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Driver ID request sent"));
		
		// Step 2 --> Check if the Driver has available status or not 
		if(driver.getStatus()==DriverStatus.AVAILABLE){
			driver.markBusy();
			kafkaService.publishDriverAssignmentAccepted(req);
			driverRepository.save(driver);
			// Publish event to Driver.Assignment.Accepted
			
		}else {
			// Publish event to Driver.Assignment.Rejected
			kafkaService.publishDriverAssignmentRejected(req);
		}
	}
}
