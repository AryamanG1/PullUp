package com.PullUp.DriverService.events;

import java.time.Instant;
import java.util.UUID;

public record DriverAssignmentRejectedEvent(
		UUID rideId,
		UUID driverId,
		String reason,
		Instant rejectedAt){}
