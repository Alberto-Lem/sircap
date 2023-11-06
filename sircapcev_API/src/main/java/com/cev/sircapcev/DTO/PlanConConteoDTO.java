package com.cev.sircapcev.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanConConteoDTO {
    private String name;
    private List<ConteoDatosDTO> series;
}
