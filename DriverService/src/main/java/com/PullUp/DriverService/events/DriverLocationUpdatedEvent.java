package com.PullUp.DriverService.events;

import java.time.Instant;
import java.util.UUID;

import com.PullUp.DriverService.model.DriverStatus;

public record DriverLocationUpdatedEvent(
UUID driverId,
Double latitude,
Double longitude,
Instant occurredAt,
DriverStatus status)
{}
