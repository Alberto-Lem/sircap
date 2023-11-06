package com.cev.sircapcev.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.PersonasEntity;

@Repository
public interface PersonasRepository extends MongoRepository<PersonasEntity, String> {
    boolean existsByDpi(String Dpi);
    Optional<PersonasEntity> findByDpi(String Dpi);
    Optional<PersonasEntity> findByNip(String nip);
    List<PersonasEntity> findByFechaDeNacimiento(LocalDate fechaDeNacimiento);

}
