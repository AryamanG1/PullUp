package com.PullUp.RiderService.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.PullUp.RiderService.kafkaservice.RideKafkaService;
import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.model.RideStatus;
import com.PullUp.RiderService.model.Rider;
import com.PullUp.RiderService.model.dto.ActiveRideRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCancellationResponseDTO;
import com.PullUp.RiderService.model.dto.RideCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RideCreationResponseDTO;
import com.PullUp.RiderService.model.dto.RideDetailsRequestDTO;
import com.PullUp.RiderService.model.dto.RideDetailsResponseDTO;
import com.PullUp.RiderService.model.dto.RiderCreationRequestDTO;
import com.PullUp.RiderService.model.dto.RiderCreationResponseDTO;
import com.PullUp.RiderService.repository.RideRepository;
import com.PullUp.RiderService.repository.RiderRepository;

import jakarta.transaction.Transactional;

@Service
public class RiderFunctionImpl implements RiderFunction {

    private final RiderRepository riderRepository;
    private final RideRepository rideRepository;
    private final RideKafkaService kafkaService;

    private static final Logger log =
            LoggerFactory.getLogger(RiderFunctionImpl.class);

    public RiderFunctionImpl(RiderRepository riderRepository, RideRepository rideRepository,
			RideKafkaService kafkaService) {
		super();
		this.riderRepository = riderRepository;
		this.rideRepository = rideRepository;
		this.kafkaService = kafkaService;
	}

	@Override
    public RiderCreationResponseDTO createProfile(RiderCreationRequestDTO req) {
        log.info("Creating rider profile");
        Rider rider = new Rider(req);
        riderRepository.save(rider);
        log.info("Rider profile created successfully, riderId={}", rider.getRiderId());
        return new RiderCreationResponseDTO(rider);
    }

    @Override
    @Transactional
    public RideCreationResponseDTO createRide(RideCreationRequestDTO req) {
        log.info("Creating ride for riderId={}", req.getRiderId());
        Ride ride = Ride.create(
                req.getRiderId(),
                req.getStartLocation(),
                req.getEndLocation()
        );
        rideRepository.save(ride);
        log.info("Ride created successfully, rideId={}, status={}",
                ride.getRideId(),
                ride.getStatus());

        kafkaService.publishRideRequestedEvent(ride);
        log.info("Ride request published Sucessfully , rideId={}, status={}",
                ride.getRideId(),
                ride.getStatus());

        return new RideCreationResponseDTO(ride);
    }

    @Override
    @Transactional
    public RideCancellationResponseDTO cancelRide(RideCancellationRequestDTO req) {
        log.info("Cancellation requested for rideId={}, riderId={}",
                req.getRideId(),
                req.getRiderId());

        Ride ride = rideRepository.findById(req.getRideId())
                .orElseThrow(() -> {
                    log.warn("Ride not found for cancellation, rideId={}",
                            req.getRideId());
                    return new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Invalid Ride ID Entered"
                    );
                });

        log.debug("Ride found for cancellation, currentStatus={}", ride.getStatus());

        if (!ride.getRiderId().equals(req.getRiderId())) {
            log.warn(
                "Unauthorized cancellation attempt: rideId={}, requestedBy={}, actualOwner={}",
                ride.getRideId(),
                req.getRiderId(),
                ride.getRiderId()
            );
            throw new IllegalStateException(
                    "Rider not authorized to cancel this ride");
        }

        ride.markCancellation();
        rideRepository.save(ride);

        log.info("Ride cancelled successfully, rideId={}, finalStatus={}",
                ride.getRideId(),
                ride.getStatus());

        kafkaService.publishRideCancelledEvent(ride);
        log.info("Ride cancelled published Sucessfully , rideId={}, status={}",
                ride.getRideId(),
                ride.getStatus());

        return new RideCancellationResponseDTO(ride);
    }

    @Override
    public RideDetailsResponseDTO getRide(RideDetailsRequestDTO req) {
        log.debug("Fetching ride details for rideId={}", req.getRideId());

        Ride ride = rideRepository.findById(req.getRideId())
                .orElseThrow(() -> {
                    log.warn("Ride not found while fetching details, rideId={}",
                            req.getRideId());
                    return new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Invalid Ride ID Entered"
                    );
                });

        log.debug("Ride details fetched, rideId={}, status={}",
                ride.getRideId(),
                ride.getStatus());

        return new RideDetailsResponseDTO(ride);
    }

    @Override
    public Optional<RideDetailsResponseDTO> getActiveRide(ActiveRideRequestDTO req) {
        log.debug("Fetching active ride for riderId={}", req.getRiderId());

        Optional<RideDetailsResponseDTO> result =
                rideRepository.findFirstByRiderIdAndStatusIn(
                        req.getRiderId(),
                        List.of(
                                RideStatus.REQUESTED,
                                RideStatus.SEARCHING_DRIVER,
                                RideStatus.ASSIGNED,
                                RideStatus.IN_PROGRESS
                        )
                ).map(ride -> {
                    log.debug(
                        "Active ride found, rideId={}, status={}",
                        ride.getRideId(),
                        ride.getStatus()
                    );
                    return new RideDetailsResponseDTO(ride);
                });

        if (result.isEmpty()) {
            log.info("No active ride found for riderId={}",
                    req.getRiderId());
        }

        return result;
    }
}
