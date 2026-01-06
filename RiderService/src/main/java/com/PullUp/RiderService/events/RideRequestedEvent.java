package com.PullUp.RiderService.events;

import java.time.Instant;
import java.util.UUID;

public record RideRequestedEvent(
	    UUID rideId,
	    UUID riderId,
	    double pickupLatitude,
	    double pickupLongitude,
	    Instant occurredAt
	) {}
