package com.PullUp.RiderService.model.dto;

import java.util.UUID;

public class ActiveRideRequestDTO {

    private UUID riderId;

    public ActiveRideRequestDTO() {}

    public ActiveRideRequestDTO(UUID riderId) {
        this.riderId = riderId;
    }

    public UUID getRiderId() {
        return riderId;
    }
}
