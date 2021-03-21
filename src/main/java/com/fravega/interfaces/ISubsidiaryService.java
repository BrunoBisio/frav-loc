package com.fravega.interfaces;

import com.fravega.models.Subsidiary;

public interface ISubsidiaryService {
    
    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception;

    public Subsidiary updateSubsidiary(int id, Subsidiary subsidiary);

    public boolean deleteSubsidiary(int id);

    public Subsidiary getSubsidiaryById(int id);

}
