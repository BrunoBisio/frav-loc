package com.fravega.repositories;

import com.fravega.models.Subsidiary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Integer> {

    Subsidiary findBySubsidiaryId(int subsidiaryId);
}