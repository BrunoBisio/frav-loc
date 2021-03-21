package com.fravega.interfaces;

import com.fravega.models.PickupPoint;

public interface IPickupPointService {

    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception;

    public PickupPoint updatePickupPoint(int id, PickupPoint pickupPoint);

    public boolean deletePickupPoint(int id);

    public PickupPoint getPickupPointById(int id);

}
