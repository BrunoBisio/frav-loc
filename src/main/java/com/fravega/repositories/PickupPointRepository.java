package com.fravega.repositories;

import com.fravega.models.PickupPoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupPointRepository extends JpaRepository<PickupPoint, Integer> {

    PickupPoint findByPickupPointId(int pickupPointId);
}