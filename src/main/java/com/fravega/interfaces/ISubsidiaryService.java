package com.fravega.interfaces;

import com.fravega.models.Subsidiary;

public interface ISubsidiaryService {
    
    public Subsidiary createSubsidiary(Subsidiary subsidiary) throws Exception;

    public Subsidiary updateSubsidiary(long id, Subsidiary subsidiary);

    public boolean deleteSubsidiary(long id);

    public Subsidiary getSubsidiaryById(long id);

}
