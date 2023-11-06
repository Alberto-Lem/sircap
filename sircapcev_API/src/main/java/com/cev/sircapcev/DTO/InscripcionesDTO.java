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
public class InscripcionesDTO {
    private String id;
    @NotBlank(message = "El NIP es requerido")
    private String nip;
    @NotBlank(message = "El número de plan es requerido")
    private String noPlan;
    private String estado;
    private String observaciones;
    private String usuario;
    private String tipo;

}
