package com.fravega.services;

import javax.persistence.EntityNotFoundException;

import com.fravega.interfaces.ISubsidiaryService;
import com.fravega.models.Subsidiary;
import com.fravega.repositories.SubsidiaryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubsidiaryService implements ISubsidiaryService {
    
    @Autowired
    private SubsidiaryRepository repository;
    @Autowired
    private NodeService nodeService;

    /// Subsidiary ABM
    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception {
        if (getSubsidiaryByLocation(subsidiary.getLongitude(), subsidiary.getLatitude()) != null)
            throw new Exception("Ya existe una sucursal en esta ubicación");
        Subsidiary savedSubsidiary = repository.save(subsidiary);
        nodeService.updateGridForAdd(savedSubsidiary);
        return savedSubsidiary;
    }

    private Object getSubsidiaryByLocation(double longitude, double latitude) {
        return null;
    }

    public Subsidiary updateSubsidiary(int id, Subsidiary subsidiary) {
        Subsidiary savedSubsidiary = getSubsidiaryById(id);
        savedSubsidiary.setAddress(subsidiary.getAddress());
        savedSubsidiary.setOpenFrom(subsidiary.getOpenFrom());
        savedSubsidiary.setOpenTo(subsidiary.getOpenTo());
        return repository.save(savedSubsidiary);
    }

    public boolean deleteSubsidiary(int id) {
        try {
            repository.delete(getSubsidiaryById(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Subsidiary getSubsidiaryById(int id) {
        Subsidiary savedSubsidiary = repository.findById(id).get();
        if (savedSubsidiary == null)
            throw new EntityNotFoundException(String.format("La sucursal con Id {0} no existe", id));
        return savedSubsidiary;
    }

}
