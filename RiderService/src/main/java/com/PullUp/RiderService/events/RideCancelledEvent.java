package com.PullUp.RiderService.events;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.RiderService.model.CancelledBy;

public record RideCancelledEvent(
	    UUID rideId,
	    UUID riderId,
	    CancelledBy cancelledBy,
	    String reason,
	    Instant occurredAt
	) {}
