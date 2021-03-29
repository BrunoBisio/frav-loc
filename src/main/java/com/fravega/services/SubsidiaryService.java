package com.fravega.services;

import javax.persistence.EntityNotFoundException;

import com.fravega.interfaces.INodeService;
import com.fravega.interfaces.IQuadtreeService;
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
    private INodeService nodeService;
    @Autowired
    private IQuadtreeService quadtreeService;

    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception {
        if (nodeService.getNodeByPosition(subsidiary.getLatitude(), subsidiary.getLongitude()) != null)
            throw new Exception("Ya se ha registrado una sucursal o punto de venta en esta ubicación");
        Subsidiary savedSubsidiary = repository.save(subsidiary);
        quadtreeService.addNodeToGrid(savedSubsidiary);
        return savedSubsidiary;
    }

    public Subsidiary updateSubsidiary(long id, Subsidiary subsidiary) {
        Subsidiary savedSubsidiary = getSubsidiaryById(id);
        savedSubsidiary.setAddress(subsidiary.getAddress());
        savedSubsidiary.setOpenFrom(subsidiary.getOpenFrom());
        savedSubsidiary.setOpenTo(subsidiary.getOpenTo());
        return repository.save(savedSubsidiary);
    }

    public boolean deleteSubsidiary(long id) {
        try {
            repository.delete(getSubsidiaryById(id));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Subsidiary getSubsidiaryById(long id) {
        Subsidiary savedSubsidiary = repository.findById(id).get();
        if (savedSubsidiary == null)
            throw new EntityNotFoundException(String.format("La sucursal con Id {0} no existe", id));
        return savedSubsidiary;
    }

}
