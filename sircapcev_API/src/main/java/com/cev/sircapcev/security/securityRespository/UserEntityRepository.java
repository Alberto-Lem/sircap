package com.cev.sircapcev.security.securityRespository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.security.securityEntity.UserEntity;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, String>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    Optional<UserEntity> findByUsername(String username);
    
}
