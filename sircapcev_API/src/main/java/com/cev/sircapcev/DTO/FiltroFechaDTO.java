package com.cev.sircapcev.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FiltroFechaDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
