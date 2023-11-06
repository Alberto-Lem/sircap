package com.cev.sircapcev.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "bitacoras")
public class BitacorasEntity {
    @Id
    private String id;
    private String username;
    private Date loginTime;
    private String accion;
    private boolean successful;
}
