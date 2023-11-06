package com.cev.sircapcev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.BitacorasEntity;

@Repository
public interface BitacorasRepository extends MongoRepository<BitacorasEntity, String> {
}
