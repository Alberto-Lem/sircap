package com.cev.sircapcev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cev.sircapcev.entity.AuditLogEntity;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLogEntity, String> {
}
