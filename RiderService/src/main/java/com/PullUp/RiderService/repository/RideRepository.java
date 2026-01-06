package com.PullUp.RiderService.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PullUp.RiderService.model.Ride;
import com.PullUp.RiderService.model.RideStatus;

@Repository
public interface RideRepository extends JpaRepository<Ride,UUID>{
	Optional<Ride> findFirstByRiderIdAndStatusIn(UUID riderId,Iterable<RideStatus> statuses);
}
