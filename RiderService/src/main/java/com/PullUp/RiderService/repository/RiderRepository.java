package com.PullUp.RiderService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PullUp.RiderService.model.Rider;


@Repository
public interface RiderRepository extends JpaRepository<Rider,UUID> {

}
