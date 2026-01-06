package com.PullUp.DriverService.events;

import java.time.Instant;
import java.util.UUID;

public record DriverAssignmentProposedEvent(
    	UUID rideId,
    	UUID driverId,
    	Double pickupLatitude,
    	Double pickupLongitude,
    	Instant proposedAt){
}
