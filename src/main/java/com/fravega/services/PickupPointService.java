package com.fravega.services;

import javax.persistence.EntityNotFoundException;

import com.fravega.interfaces.IPickupPointService;
import com.fravega.models.PickupPoint;
import com.fravega.repositories.PickupPointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickupPointService implements IPickupPointService {
    
    @Autowired
    private PickupPointRepository repository;
    @Autowired
    private NodeService nodeService;
    
    /// PickupPoint ABM
    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception {
        if (getPickupPointByPosition(pickupPoint.getLongitude(), pickupPoint.getLatitude()) != null)
            throw new Exception("Ya existe un punto de retiro en esta ubicación");
        PickupPoint savedPickupPoint = repository.save(pickupPoint);
        nodeService.updateGridForAdd(savedPickupPoint);
        return savedPickupPoint;
    }

    private Object getPickupPointByPosition(double longitude, double latitude) {
        return null;
    }

    public PickupPoint updatePickupPoint(int id, PickupPoint pickupPoint) {
        PickupPoint savedPickupPoint = getPickupPointById(id);
        savedPickupPoint.setCapacity(pickupPoint.getCapacity());
        return repository.save(savedPickupPoint);
    }

    public boolean deletePickupPoint(int id) {
        try {
            repository.delete(getPickupPointById(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public PickupPoint getPickupPointById(int id) {
        PickupPoint savedPickupPoint = repository.findById(id).get();
        if (savedPickupPoint == null)
            throw new EntityNotFoundException(String.format("El Punto de retiro con Id {0} no existe", id));
        return savedPickupPoint;
    }

}