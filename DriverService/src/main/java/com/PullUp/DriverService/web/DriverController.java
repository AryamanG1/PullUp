package com.PullUp.DriverService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.PullUp.DriverService.model.dto.DriverCreationRequestDTO;
import com.PullUp.DriverService.model.dto.DriverCreationResponseDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsRequestDTO;
import com.PullUp.DriverService.model.dto.DriverDetailsResponseDTO;
import com.PullUp.DriverService.model.dto.DriverLocationUpdateRequestDTO;
import com.PullUp.DriverService.model.dto.DriverStatusChangeRequestDTO;
import com.PullUp.DriverService.response.ApiResponse;
import com.PullUp.DriverService.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // 1️ Create Driver
    @PostMapping("/drivers")
    public ResponseEntity<ApiResponse<?>> createDriver(
            @RequestBody @Valid DriverCreationRequestDTO req) {

        try {
            DriverCreationResponseDTO res =
                    driverService.createDriver(req);

            return ResponseEntity.ok(
                ApiResponse.success("Driver created successfully", res)
            );

        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                ApiResponse.failure(e.getReason()),
                e.getStatusCode()
            );
        }
    }

    // 2️ Get Driver Details
    @PostMapping("/drivers/details")
    public ResponseEntity<ApiResponse<?>> getDriverDetails(
            @RequestBody @Valid DriverDetailsRequestDTO req) {

        try {
            DriverDetailsResponseDTO res =
                    driverService.getDriverDetails(req);

            return ResponseEntity.ok(
                ApiResponse.success("Driver details fetched successfully", res)
            );

        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                ApiResponse.failure(e.getReason()),
                e.getStatusCode()
            );
        }
    }

    // 3️ Change Driver Status
    @PostMapping("/drivers/status")
    public ResponseEntity<ApiResponse<?>> changeDriverStatus(
            @RequestBody @Valid DriverStatusChangeRequestDTO req) {

        try {
            DriverDetailsResponseDTO res =
                    driverService.changeDriverStatus(req);

            return ResponseEntity.ok(
                ApiResponse.success("Driver status updated successfully", res)
            );

        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                ApiResponse.failure(e.getReason()),
                e.getStatusCode()
            );
        }
    }
    
    
    @PostMapping("/drivers/location")
    public ResponseEntity<ApiResponse<?>> updateDriverLocation(
            @RequestBody @Valid DriverLocationUpdateRequestDTO req) {

        try {
            DriverDetailsResponseDTO res =
                driverService.updateDriverLocation(req);

            return ResponseEntity.ok(
                ApiResponse.success("Driver location updated", res)
            );

        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(
                ApiResponse.failure(e.getReason()),
                e.getStatusCode()
            );
        }
    }

}
