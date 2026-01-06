package com.PullUp.DriverService.kafkaservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.PullUp.DriverService.events.DriverLocationUpdatedEvent;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.redisservice.DriverGEOIndex;

@Service
public class DriverLocationUpdatedConsumer {
	private final DriverGEOIndex geoIndex;
	private static final Logger log =
            LoggerFactory.getLogger(DriverLocationUpdatedConsumer.class);

	public DriverLocationUpdatedConsumer(DriverGEOIndex geoIndex) {
		super();
		this.geoIndex = geoIndex;
	}
	
	@KafkaListener(
			topics="driver.location.updated")
	public void onDriverLocationUpdatedEvent(@Payload DriverLocationUpdatedEvent event){
		if(event.status()==DriverStatus.AVAILABLE){
			log.info("🔥 DRIVER STATUS EVENT RECEIVED: {}", event);
			geoIndex.updateLocation(event.driverId().toString(), event.longitude(),event.latitude());
		}
	}
	
	
}
