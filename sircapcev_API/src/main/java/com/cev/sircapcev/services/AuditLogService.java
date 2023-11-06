package com.cev.sircapcev.services;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cev.sircapcev.entity.AuditLogEntity;
import com.cev.sircapcev.repositories.AuditLogRepository;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logEvent(String eventDescription) {
        AuditLogEntity log = new AuditLogEntity();
        log.setEventDescription(eventDescription);
        log.setEventDate(new Date());
        auditLogRepository.save(log);
    }
}
