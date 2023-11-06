package com.cev.sircapcev.DTO;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AsignaturasDTO {
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String nombre;
    @NotBlank(message = "Credito is mandatory")
    private Integer credito;
    private String usuario;
    private String estado;
    private String niveles;

}
