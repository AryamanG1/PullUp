package com.PullUp.DriverService.kafkaservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.PullUp.DriverService.events.DriverStatusChangedEvent;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.redisservice.DriverGEOIndex;

@Service
public class DriverStatusChangedConsumer {
	private final DriverGEOIndex geoIndex;
	private static final Logger log =
            LoggerFactory.getLogger(DriverStatusChangedConsumer.class);
	public DriverStatusChangedConsumer(DriverGEOIndex geoIndex) {
		super();
		this.geoIndex = geoIndex;
	}
	
	@KafkaListener(
			topics="driver.status.changed")
	public void onDriverLocationUpdatedEvent(@Payload DriverStatusChangedEvent event){
		log.info("🔥 DRIVER STATUS EVENT RECEIVED: {}", event);
		if(event.newStatus()==DriverStatus.AVAILABLE){
			geoIndex.addAvailableDriver(event.driverId().toString(), event.longitude(), event.latitude());
		}else{
			geoIndex.removeDriver(event.driverId().toString());
		}
	}
	
}
