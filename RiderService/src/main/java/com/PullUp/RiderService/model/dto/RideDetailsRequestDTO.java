package com.PullUp.RiderService.model.dto;

import java.util.UUID;

public class RideDetailsRequestDTO {

    private UUID rideId;

    public RideDetailsRequestDTO() {}

    public RideDetailsRequestDTO(UUID rideId) {
        this.rideId = rideId;
    }

    public UUID getRideId() {
        return rideId;
    }
}
