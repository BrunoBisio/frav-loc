package com.fravega.interfaces;

import com.fravega.models.PickupPoint;
import com.fravega.models.Subsidiary;

import ch.qos.logback.core.pattern.parser.Node;

public interface IABMService {

    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception;

    public PickupPoint updatePickupPoint(int id, PickupPoint pickupPoint);

    public boolean deletePickupPoint(int id);

    public PickupPoint getPickupPointById(int id);

    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception;

    public Subsidiary updateSubsidiary(int id, Subsidiary subsidiary);

    public boolean deleteSubsidiary(int id);

    public Subsidiary getSubsidiaryById(int id);

    public Node pickClosest(long latitude, long longitude) throws Exception;
}
