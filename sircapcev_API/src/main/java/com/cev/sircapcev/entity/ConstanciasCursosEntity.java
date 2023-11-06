package com.cev.sircapcev.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "constanciaCursos")
public class ConstanciasCursosEntity{
    @Id
    private String id;
    private String gradoPolicial;
    private String nombres;
    private String apellidos;
    private String nip;
     @Field(name = "asignaturas")
    private List<String> asignaturas;
    private String aprobacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String descripcion;
    private String documentoRespaldo;
    private String estado;
    private String usuario;
}
