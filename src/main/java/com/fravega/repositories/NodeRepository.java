package com.fravega.repositories;

import com.fravega.models.Node;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository<T extends Node> extends JpaRepository<T, Long> {
}