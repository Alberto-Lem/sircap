package com.cev.sircapcev.repositories;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.AsignaturasEntity;

@Repository
public interface AsignaturasRepository extends MongoRepository<AsignaturasEntity, String> {
    boolean existsByNombre(String nombre);
    Optional<AsignaturasEntity> findByNombre(String nombre);
    
}
