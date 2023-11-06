package com.cev.sircapcev.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanesDTO {
    private String id;

    @NotBlank(message = "No Plan asignatura is mandatory")
    private String noPlan;

    @NotBlank(message = "Nombre Plan asignatura is mandatory")
    private String nombrePlan;
    @NotNull
    @Min(value = 0, message = "La cantidad de participantes debe ser un número positivo.")
    private int cantidadParticipante;

    @NotBlank(message = "Estado del plan es obligatorio")
    private String estado;

    @NotNull(message = "Fecha de Inicio del plan es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "Fecha de Finalizacion del plan es obligatorio")
    private LocalDate fechaFinalizacion;

    @NotBlank(message = "Modalidad del plan es obligatorio")
    private String modalidad;

    @NotBlank(message = "Digitar a quien va dirigido el plan")
    private String dirigidoA;

    @NotBlank(message = "Encargado asignatura is mandatory")
    private String encargado;

    @NotBlank(message = "Lugar de ejecucion del plan es obligatorio")
    private String lugarEjecucion;

    private String comentario;
}