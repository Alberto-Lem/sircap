package com.cev.sircapcev.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "asignaturas")
public class AsignaturasEntity {
    @Id
    private String id;
    private String nombre;
    private Integer Credito;
    private LocalDateTime fechaCreacion;
    private LocalDateTime FechaActualizacion;
    private String usuario;
    private String estado;
    private String niveles;
}
