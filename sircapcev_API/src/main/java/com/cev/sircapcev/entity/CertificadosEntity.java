package com.cev.sircapcev.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "certificados")
public class CertificadosEntity {
    @Id
    private String id;
    private String nombreEstudiante;
    private String nombreCurso;
    private LocalDate fechaEmision;
    private String codigoUnico;
    private String urlVerificacion;
}
