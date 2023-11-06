package com.cev.sircapcev.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.ConstanciasCursosEntity;

@Repository
public interface ConstanciasCursosRepository extends MongoRepository<ConstanciasCursosEntity, String> {
    boolean existsByNip(String Nip);
    Optional<ConstanciasCursosEntity> findByNip(String Nip);
}
