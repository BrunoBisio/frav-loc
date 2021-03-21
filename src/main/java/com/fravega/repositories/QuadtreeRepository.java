package com.fravega.repositories;

import java.util.List;

import com.fravega.models.Quadtree;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadtreeRepository extends JpaRepository<Quadtree, Long> {
    
    List<Quadtree> findAll();

    Quadtree findFirstByOrderByIdAsc();
}
