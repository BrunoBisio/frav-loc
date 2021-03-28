package com.fravega.repositories;

import com.fravega.models.PickupPoint;

import org.springframework.stereotype.Repository;

@Repository
public interface PickupPointRepository extends NodeRepository<PickupPoint> {

    PickupPoint findByPickupPointId(long pickupPointId);
}