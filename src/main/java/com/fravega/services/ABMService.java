package com.fravega.services;

import javax.persistence.EntityNotFoundException;

import com.fravega.interfaces.IABMService;
import com.fravega.models.PickupPoint;
import com.fravega.models.Quadtree;
import com.fravega.models.Square;
import com.fravega.models.Subsidiary;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.pattern.parser.Node;

@Service
public class ABMService implements IABMService {

    private Square boundry = new Square(0, 0, 180, 90);
    private Quadtree quadTree = new Quadtree(boundry, 4);

    /// PickupPoint ABM
    public PickupPoint createPickupPoint(PickupPoint pickupPoint) throws Exception {
        if (getPickupPointByPosition(pickupPoint.getLongitude(), pickupPoint.getLatitude()) != null)
            throw new Exception("Ya existe un punto de retiro en esta ubicación");
        // TODO: save pickupPoint
        // quadTree.addNode(persistedPickupPoint)
        return null;
    }

    private Object getPickupPointByPosition(double longitude, double latitude) {
        return null;
    }

    public PickupPoint updatePickupPoint(int id, PickupPoint pickupPoint) {
        PickupPoint savedPickupPoint = getPickupPointById(id);
        if (savedPickupPoint == null)
            throw new EntityNotFoundException(String.format("El Puto de retiro con Id {0} no existe", id));
        // quadTree.removeNode(savedPickupPoint);
        savedPickupPoint.setCapacity(pickupPoint.getCapacity());
        savedPickupPoint.setLatitude(pickupPoint.getLatitude());
        savedPickupPoint.setLongitude(pickupPoint.getLongitude());
        // quadTree.addNode(savedPickupPoint);
        // TODO: save pickupPoint
        return savedPickupPoint;
    }

    public boolean deletePickupPoint(int id) {
        // quadTree.removeNode(persistedPickupPoint)
        // TODO: remove the pickupPoint from database
        return true;
    }

    public PickupPoint getPickupPointById(int id) {
        PickupPoint pp = new PickupPoint(5, 15.15, 15.15);
        // TODO: logic to retrieve a pickup point
        return pp;
    }

    /// Subsidiary ABM
    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception {
        if (getSubsidiaryByLocation(subsidiary.getLongitude(), subsidiary.getLatitude()) != null)
            throw new Exception("Ya existe una sucursal en esta ubicación");
        // TODO: save subsidiary
        // quadTree.addNode(persistedSubsidiary)
        return null;
    }

    private Object getSubsidiaryByLocation(double longitude, double latitude) {
        return null;
    }

    public Subsidiary updateSubsidiary(int id, Subsidiary subsidiary) {
        Subsidiary savedSubsidiary = getSubsidiaryById(id);
        if (savedSubsidiary == null)
            throw new EntityNotFoundException(String.format("La sucursal con Id {0} no existe", id));
        // quadTree.removeNode(savedPickupPoint);
        savedSubsidiary.setAddress(subsidiary.getAddress());
        savedSubsidiary.setOpenFrom(subsidiary.getOpenFrom());
        savedSubsidiary.setOpenTo(subsidiary.getOpenTo());
        savedSubsidiary.setLatitude(subsidiary.getLatitude());
        savedSubsidiary.setLongitude(subsidiary.getLongitude());
        // quadTree.addNode(persistedSubsidiary)
        // TODO: save subsidiary
        return savedSubsidiary;
    }

    public boolean deleteSubsidiary(int id) {
        // quadTree.removeNode(persistedSubsidiary)
        // TODO: remove the subsidiary from database
        return true;
    }

    public Subsidiary getSubsidiaryById(int id) {
        Subsidiary subsidiary = new Subsidiary("bazurco 2033", 8, 16, 15.15, 15.15);
        // TODO: logic to retrieve a subsidiary
        return subsidiary;
    }

    @Override
    public Node pickClosest(long latitude, long longitude) throws Exception {
        Node node = quadTree.getNodeByLocation(latitude, longitude);
        if (node == null)
            throw new Exception("No se encontro ningun local ni punto de retiro en el area");
        return node;
    }
}