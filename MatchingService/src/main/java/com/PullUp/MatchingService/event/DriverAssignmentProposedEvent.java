package com.PullUp.MatchingService.event;

import java.time.Instant;
import java.util.UUID;

public record DriverAssignmentProposedEvent(
	    	UUID rideId,
	    	UUID driverId,
	    	Double pickupLatitude,
	    	Double pickupLongitude,
	    	Instant proposedAt){
}
