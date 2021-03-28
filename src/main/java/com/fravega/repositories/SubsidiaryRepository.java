package com.fravega.repositories;

import com.fravega.models.Subsidiary;

import org.springframework.stereotype.Repository;

@Repository
public interface SubsidiaryRepository extends NodeRepository<Subsidiary> {

    Subsidiary findBySubsidiaryId(long subsidiaryId);
}