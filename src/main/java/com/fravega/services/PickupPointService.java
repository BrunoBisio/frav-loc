package com.fravega.services;

import javax.persistence.EntityNotFoundException;

import com.fravega.interfaces.INodeService;
import com.fravega.interfaces.IPickupPointService;
import com.fravega.interfaces.IQuadtreeService;
import com.fravega.models.PickupPoint;
import com.fravega.repositories.PickupPointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickupPointService implements IPickupPointService {
    
    @Autowired
    private PickupPointRepository repository;
    @Autowired
    private INodeService nodeService;
    @Autowired
    private IQuadtreeService quadtreeService;
    
    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception {
        if (nodeService.getNodeByPosition(pickupPoint.getLatitude(), pickupPoint.getLongitude()) != null)
            throw new Exception("Ya se ha registrado una sucursal o punto de venta en esta ubicación");
        PickupPoint savedPickupPoint = repository.save(pickupPoint);
        quadtreeService.updateGridForAdd(savedPickupPoint);
        return savedPickupPoint;
    }

    public PickupPoint updatePickupPoint(long id, PickupPoint pickupPoint) {
        PickupPoint savedPickupPoint = getPickupPointById(id);
        savedPickupPoint.setCapacity(pickupPoint.getCapacity());
        return repository.save(savedPickupPoint);
    }

    public boolean deletePickupPoint(long id) {
        try {
            repository.delete(getPickupPointById(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public PickupPoint getPickupPointById(long id) {
        PickupPoint savedPickupPoint = repository.findById(id).get();
        if (savedPickupPoint == null)
            throw new EntityNotFoundException(String.format("El Punto de retiro con Id {0} no existe", id));
        return savedPickupPoint;
    }

}
