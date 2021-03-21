package com.fravega.controllers;

import com.fravega.interfaces.INodeService;
import com.fravega.models.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NodeController {

    @Autowired
    private INodeService service;
    
    @GetMapping("/puntoMasCercano")
    public Node getClosestNode(@RequestBody long latitude, long longitude) throws Exception {
        return service.getClosestNode(latitude, longitude);
    }

}
