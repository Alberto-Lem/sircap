package com.cev.sircapcev.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cev.sircapcev.entity.CiclosEntity;

@Repository
public interface CiclosRepository extends MongoRepository<CiclosEntity, String> {
     boolean existsByNombre(String nombre);
    Optional<CiclosEntity> findByNombre(String nombre);
    
}
