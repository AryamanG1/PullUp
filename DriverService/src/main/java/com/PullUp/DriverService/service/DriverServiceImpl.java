package com.PullUp.DriverService.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.PullUp.DriverService.kafkaservice.DriverKafkaService;
import com.PullUp.DriverService.model.DriverStatus;
import com.PullUp.DriverService.model.RideDriver;
import com.PullUp.DriverService.model.dto.DriverCreationRequestDTO;
import com.PullUp.DriverService.model.dto.DriverCreationResponseDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsRequestDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsResponseDTO;
import com.PullUp.DriverService.model.dto.DriverLocationUpdateRequestDTO;
import com.PullUp.DriverService.model.dto.DriverStatusChangeRequestDTO;
import com.PullUp.DriverService.repository.DriverRepository;

import jakarta.transaction.Transactional;

@Service
public class DriverServiceImpl implements DriverService {
	
	private final DriverRepository driverRepository;
	private final DriverKafkaService kafkaService;

	
	public DriverServiceImpl(DriverRepository driverRepository, DriverKafkaService kafkaService) {
		super();
		this.driverRepository = driverRepository;
		this.kafkaService = kafkaService;
	}

	@Override
	public DriverCreationResponseDTO createDriver(DriverCreationRequestDTO req) {
		RideDriver driver = new RideDriver(req);
		driverRepository.save(driver);
		return new DriverCreationResponseDTO(driver);
	}

	@Override
	public DriverDetailsResponseDTO getDriverDetails(DriverDetailsRequestDTO req) {
		RideDriver driver = driverRepository.findById(req.getDriverId())
				.orElseThrow(() -> new ResponseStatusException(
		                HttpStatus.BAD_REQUEST,
		                "Invalid Driver ID"
		            ));
		
		return new DriverDetailsResponseDTO(driver);
	}

	@Override
	@Transactional
	public DriverDetailsResponseDTO changeDriverStatus(DriverStatusChangeRequestDTO req) {
		RideDriver driver = driverRepository.findById(req.getDriverId())
				.orElseThrow(() -> new ResponseStatusException(
		                HttpStatus.BAD_REQUEST,
		                "Invalid Driver ID"
		            ));
		DriverStatus oldStatus = driver.getStatus();
		 switch (req.getNewStatus()) {
	        case AVAILABLE -> driver.markAvailable();
	        case BUSY -> driver.markBusy();
	        case OFFLINE -> driver.markOffline();
	    }
		driverRepository.save(driver);
		kafkaService.publishDriverStatusChangedEvent(driver,oldStatus);
		return new DriverDetailsResponseDTO(driver);
		
	}
	
	@Override
	@Transactional
	public DriverDetailsResponseDTO updateDriverLocation(
	        DriverLocationUpdateRequestDTO req) {

	    RideDriver driver = driverRepository.findById(req.getDriverId())
	        .orElseThrow(() ->
	            new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "Invalid Driver ID"
	            )
	        );

	    driver.updateLocation(
	        req.getLatitude(),
	        req.getLongitude()
	    );

	    driverRepository.save(driver);
	    kafkaService.publishDriverLocationUpdatedEvent(driver);
	    return new DriverDetailsResponseDTO(driver);
	}


}
