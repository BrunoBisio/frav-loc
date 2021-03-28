package com.fravega.interfaces;

import com.fravega.models.PickupPoint;

public interface IPickupPointService {

    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception;

    public PickupPoint updatePickupPoint(long id, PickupPoint pickupPoint);

    public boolean deletePickupPoint(long id);

    public PickupPoint getPickupPointById(long id);

}
