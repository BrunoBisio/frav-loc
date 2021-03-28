package com.fravega.controllers;

import com.fravega.interfaces.IPickupPointService;
import com.fravega.models.PickupPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PickupPointController {
    
    @Autowired
    private IPickupPointService service;

    @PostMapping("/puntoDeRetiro")
    public PickupPoint addPickupPoint(@RequestBody PickupPoint pickupPoint) throws Exception {
        return service.createPickupPoint(pickupPoint);
    }

    @PutMapping("/puntoDeRetiro/{id}")
    public PickupPoint updatePickupPoint(@RequestParam long id, @RequestBody PickupPoint pickupPoint) {
        return service.updatePickupPoint(id, pickupPoint);
    }

    @GetMapping("/puntoDeRetiro/{id}")
    public PickupPoint getPickupPoint(@RequestParam long id) {
        return service.getPickupPointById(id);
    }

    @DeleteMapping("/puntoDeRetiro/{id}")
    public boolean deletePickupPoint(@RequestParam long id) {
        return service.deletePickupPoint(id);
    }
}
