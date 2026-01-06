package com.PullUp.DriverService.events;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.DriverService.model.DriverStatus;

public record DriverStatusChangedEvent(
		UUID driverId,
		DriverStatus oldStatus,
		DriverStatus newStatus,
		Double latitude,
		Double longitude,
		Instant occurredAt
		) 

{}
