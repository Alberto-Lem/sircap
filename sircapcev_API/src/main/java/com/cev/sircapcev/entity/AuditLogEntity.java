package com.cev.sircapcev.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "auditLogs")
public class AuditLogEntity {
    @Id
    private String id;
    private String eventDescription;
    private Date eventDate;
}
