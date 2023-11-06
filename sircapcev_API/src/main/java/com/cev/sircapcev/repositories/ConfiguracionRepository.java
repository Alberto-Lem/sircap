package com.cev.sircapcev.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.ConfiguracionEntity;

@Repository
public interface ConfiguracionRepository extends MongoRepository<ConfiguracionEntity, String> {
    Optional<ConfiguracionEntity> findByConfigKey(String configKey);
    boolean existsByConfigKey(String configKey);
}
