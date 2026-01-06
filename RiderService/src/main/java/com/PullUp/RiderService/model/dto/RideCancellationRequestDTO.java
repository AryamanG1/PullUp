package com.PullUp.RiderService.model.dto;

import java.util.UUID;

public class RideCancellationRequestDTO {

    private UUID rideId;
    private UUID riderId;

    public RideCancellationRequestDTO() {}

    public RideCancellationRequestDTO(UUID rideId, UUID riderId) {
        this.rideId = rideId;
        this.riderId = riderId;
    }

    public UUID getRideId() {
        return rideId;
    }

    public UUID getRiderId() {
        return riderId;
    }
}
