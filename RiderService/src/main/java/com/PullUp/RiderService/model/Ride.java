package com.PullUp.RiderService.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID rideId;
	
	private UUID riderId;
	private UUID driverId;
	
	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name = "latitude", column = @Column(name = "start_lat")),
	    @AttributeOverride(name = "longitude", column = @Column(name = "start_lng"))
	})
	private GeoLocation startLocation;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "latitude" , column = @Column(name = "end_lat")),
		@AttributeOverride(name = "longitude" , column = @Column(name = "end_lng"))
	})
	private GeoLocation endLocation;
	
	private Instant createdAt;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RideStatus status;
	
	protected Ride(){}

	// Factory Method For Creating object 
	public static Ride create(UUID riderId , GeoLocation startLoc , GeoLocation endLoc ){
		Ride ride = new Ride();
		ride.riderId = riderId;
		ride.endLocation = endLoc;
		ride.startLocation = startLoc;
		ride.status = RideStatus.REQUESTED;
		return ride;
	}
	
	
	public void markSearchingDriver(){
		if(status!=RideStatus.REQUESTED){
			throw new IllegalStateException("Cannot search for Driver");
		}
		
		status = RideStatus.SEARCHING_DRIVER;
	}
	
	public void markFoundDriver(UUID driverId){
		if(status!=RideStatus.SEARCHING_DRIVER){
			throw new IllegalStateException("Cannot Assign Driver");
		}
		
		status = RideStatus.ASSIGNED;
		this.driverId = driverId;
	}
	
	public void markStartRide(){
		if(status!=RideStatus.ASSIGNED) {
			throw new IllegalStateException("Cannot Start the ride");
		}
		
		status=RideStatus.IN_PROGRESS;
	}
	
	public void markCompletion(){
		if(status!=RideStatus.IN_PROGRESS){
			throw new IllegalStateException("Cannot Complete the Ride");
		}
		
		status = RideStatus.COMPLETED;
	}
	
	public void markCancellation(){
		if(status==RideStatus.COMPLETED && status==RideStatus.CANCELLED) {
			throw new IllegalStateException("Cannot cancel the Ride");
		}
		
		status = RideStatus.CANCELLED;
	}
	
	public UUID getRideId() {
		return rideId;
	}

	public UUID getRiderId() {
		return riderId;
	}

	public UUID getDriverId() {
		return driverId;
	}

	public GeoLocation getStartLocation() {
		return startLocation;
	}

	public GeoLocation getEndLocation() {
		return endLocation;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	@PrePersist
	protected void onCreate() {
	    this.createdAt = Instant.now();
	}


	public RideStatus getStatus() {
		return status;
	}
}
