package com.PullUp.DriverService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PullUp.DriverService.model.RideDriver;

@Repository
public interface DriverRepository extends JpaRepository<RideDriver,UUID>{

}
