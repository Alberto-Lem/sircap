package com.cev.sircapcev.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.PlanesEntity;

@Repository
public interface PlanesRepository extends MongoRepository<PlanesEntity, String> {
    boolean existsByNoPlan(String noPlan);
    Optional<PlanesEntity> findByNoPlan(String noPlan);
}

