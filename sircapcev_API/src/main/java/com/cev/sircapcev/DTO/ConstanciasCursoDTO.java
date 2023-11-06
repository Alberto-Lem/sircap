package com.cev.sircapcev.DTO;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConstanciasCursoDTO {
   @Id
    private String id;
    @NotBlank(message = "Grado Policial es requerido")
    private String gradoPolicial;
    @NotBlank(message = "Nombres es requerido")
    private String nombres;
    @NotBlank(message = "Apellidos es requerido")
    private String apellidos;
    @NotBlank(message = "NIP es requerido")
    private String nip;
    @NotNull(message = "Genero es requerido")    
    private List<String> asignaturas;
    private String aprobacion;
    private String descripcion;
    @NotBlank(message = "Documento de Respaldo es requerido")
    private String documentoRespaldo;
    private String estado;
    private String usuario;
}