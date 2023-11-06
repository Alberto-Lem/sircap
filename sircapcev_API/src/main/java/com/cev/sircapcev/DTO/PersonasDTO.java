package com.cev.sircapcev.DTO;

import java.time.LocalDate;
import java.util.List;

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
public class PersonasDTO {
    private String id;
    @NotNull(message = "NIP es requerido")
    private String nip;
    @NotBlank(message = "Grado Policial es requerido")
    private String gradoPolicial;
    @NotBlank(message = "Nombres es requerido")
    private String nombres;
    @NotBlank(message = "Apellidos es requerido")
    private String apellidos;
    @NotBlank(message = "Genero es requerido")
    private String genero;
    @NotBlank(message = "DPI es requerido")
    private String dpi;
    @NotBlank(message = "NIT es requerido")
    private String nit;

    private Integer noAfiliacionIgss;
    @NotBlank(message = "Renglon Presupuestario es requerido")
    private String renglonPresupuestario;
    @NotNull(message = "Fecha de Nacimiento es requerido")
    private LocalDate fechaDeNacimiento;
    private Integer edad;

    private String direccionResidencial;
    @NotBlank(message = "Lugar de Servicio es requerido")
    private String lugarDeServicio;
    @NotBlank(message = "Destino Actual es requerido")
    private String destinoActual;

    private String dependenciaDondeLabora;
    @NotBlank(message = "Telefono es requerido")
    private String telefono;
    @NotBlank(message = "Correo Electronico Personal es requerido")
    private String correoElectronicoPersonal;
    private String correoElectronicoInstitucional;
    @NotBlank(message = "Grupo Etnico es requerido")
    private String grupoEtnico;
    @NotBlank(message = "Escolaridad es requerido")
    private String escolaridad;
    @NotBlank(message = "Titulo Obtenido es requerido")
    private String tituloObtenido;
    private String numeroColegiadoActivo;
    @NotNull(message = "Idiomas que domina es requerido")
    private List<String> idiomasQueDomina;
    private String estudiaActualmente;
    private String nombreCarrera;
    private String otrosEstudiosRealizados;
}

