package com.cev.sircapcev.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.cev.sircapcev.entity.InscripcionesEntity;

@Repository
public interface InscripcionesRepository extends MongoRepository<InscripcionesEntity, String> {
    boolean existsByNipAndNoPlan(String nip, String noPlan);
    Optional<InscripcionesEntity> findByNipAndNoPlan(String nip, String noPlan);
}
