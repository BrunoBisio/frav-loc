package com.fravega.controllers;

import com.fravega.interfaces.ISubsidiaryService;
import com.fravega.models.Subsidiary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sucursal")
public class SubsidiaryController {

    @Autowired
    private ISubsidiaryService service;

    @PostMapping("/")
    public Subsidiary newSubsidiary(@RequestBody Subsidiary subsidiary) throws Exception {
        return service.createSubsidiary(subsidiary);
    }

    @PutMapping("/{id}")
    public Subsidiary updateSubsidiary(@RequestParam long id, @RequestBody Subsidiary subsidiary) {
        return service.updateSubsidiary(id, subsidiary);
    }

    @GetMapping("/{id}")
    public Subsidiary getSubsidiary(@RequestParam long id) {
        return service.getSubsidiaryById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSubsidiary(@RequestParam long id) {
        return service.deleteSubsidiary(id);
    }
}
