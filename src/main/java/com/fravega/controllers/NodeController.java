package com.fravega.controllers;

import com.fravega.interfaces.INodeService;
import com.fravega.models.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/puntoMasCercano")
public class NodeController {

    @Autowired
    private INodeService service;
    
    @GetMapping("/{longitude}/{latitude}")
    public Node getClosestNode(@RequestParam long longitude,@RequestParam long latitude) throws Exception {
        return service.getClosestNode(longitude, latitude);
    }

}
