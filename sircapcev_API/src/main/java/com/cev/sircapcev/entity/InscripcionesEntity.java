package com.cev.sircapcev.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "inscripciones")
public class InscripcionesEntity{
    @Id
    private String id;
    private String nip;
    private String noPlan;
    private String estado;    
    private String observaciones;
    private LocalDate fechaInscripcion;
    private LocalTime horaInscripcion;
    private LocalDate fechaModificacion;
    private LocalTime horaModificacion;
    @CreatedBy
    private String hechoPor;
    @LastModifiedBy
    private String ultimaModificacionPor;
    private String tipo;
    @DBRef
    private PersonasEntity persona;
    @DBRef
    private PlanesEntity plan;
}
