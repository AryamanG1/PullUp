package com.PullUp.DriverService.service;

import com.PullUp.DriverService.model.dto.DriverCreationRequestDTO;
import com.PullUp.DriverService.model.dto.DriverCreationResponseDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsRequestDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsResponseDTO;
import com.PullUp.DriverService.model.dto.DriverLocationUpdateRequestDTO;
import com.PullUp.DriverService.model.dto.DriverStatusChangeRequestDTO;

public interface DriverService {

	public DriverCreationResponseDTO createDriver(DriverCreationRequestDTO req);
	public DriverDetailsResponseDTO getDriverDetails(DriverDetailsRequestDTO req);
	public DriverDetailsResponseDTO changeDriverStatus(DriverStatusChangeRequestDTO req);
	public DriverDetailsResponseDTO updateDriverLocation(DriverLocationUpdateRequestDTO req);
}
