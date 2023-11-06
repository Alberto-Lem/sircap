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
public class CiclosDTO {
    private String id;
    @NotBlank(message = "Nombre is mandatory")
    private String nombre;
    @NotBlank(message = "descripcion is mandatory")
    private String description;
}
