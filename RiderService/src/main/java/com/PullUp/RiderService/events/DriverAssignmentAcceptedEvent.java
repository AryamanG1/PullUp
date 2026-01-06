package com.PullUp.RiderService.events;

import java.time.Instant;
import java.util.UUID;

public record DriverAssignmentAcceptedEvent(
		UUID rideId,
		UUID driverId,
		Instant acceptedAt){}
