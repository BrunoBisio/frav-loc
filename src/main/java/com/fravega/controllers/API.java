package com.fravega.controllers;

import com.fravega.models.PickupPoint;
import com.fravega.models.Subsidiary;
import com.fravega.interfaces.IABMService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.pattern.parser.Node;

@RestController
public class API {

    @Autowired
    private IABMService service;
    
    @PostMapping("/puntoDeRetiro")
    public PickupPoint newPickupPoint(@RequestBody PickupPoint pickupPoint) throws Exception {
        return service.createPickupPoint(pickupPoint);
    }

    @PostMapping("/puntoDeRetiro/{id}")
    public PickupPoint updatePickupPoint(@RequestParam int id, @RequestBody PickupPoint pickupPoint) {
        return service.updatePickupPoint(id, pickupPoint);
    }

    @GetMapping("/puntoDeRetiro/{id}")
    public PickupPoint getPickupPoint(@RequestParam int id) {
        return service.getPickupPointById(id);
    }

    @DeleteMapping("/puntoDeRetiro/{id}")
    public boolean deletePickupPoint(@RequestParam int id) {
        return service.deletePickupPoint(id);
    }

    @PostMapping("/sucursal")
    public Subsidiary newSubsidiary(@RequestBody Subsidiary subsidiary) throws Exception {
        return service.createSubsidiary(subsidiary);
    }

    @PostMapping("/sucursal/{id}")
    public Subsidiary updateSubsidiary(@RequestParam int id, @RequestBody Subsidiary subsidiary) {
        return service.updateSubsidiary(id, subsidiary);
    }

    @GetMapping("/sucursal/{id}")
    public Subsidiary getSubsidiary(@RequestParam int id) {
        return service.getSubsidiaryById(id);
    }

    @DeleteMapping("/sucursal/{id}")
    public boolean deleteSubsidiary(@RequestParam int id) {
        return service.deleteSubsidiary(id);
    }

    @GetMapping("/puntoMasCercano")
    public Node getClosestNode(@RequestBody long latitude, long longitude) throws Exception {
        return service.pickClosest(latitude, longitude);
    }
    
}
