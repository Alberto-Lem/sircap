package com.cev.sircapcev.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "planes")
public class PlanesEntity {
    @Id
    private String id;
    private String noPlan;
    private String nombrePlan;
    private int cantidadPerticipante;
    private String estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private String modalidad;
    private String dirigidoA;
    private String encargado;
    private String lugarEjecucion;
    private String comentario;
}