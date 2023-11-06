package com.cev.sircapcev.entity;

import java.time.LocalDate;
import java.time.Period;
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
@Document(collection = "personas")
public class PersonasEntity {
    @Id
    private String id;
    private String nip;
    private String gradoPolicial;
    private String nombres;
    private String apellidos;
    private String genero;
    private String dpi;
    private String nit;
    private Integer noAfiliacionIgss;
    private String renglonPresupuestario;
    private LocalDate fechaDeNacimiento;
    private String direccionResidencial;
    private String lugarDeServicio;
    private String destinoActual;
    private String dependenciaDondeLabora;
    private String telefono;
    private String correoElectronicoPersonal;
    private String correoElectronicoInstitucional;
    private String grupoEtnico;
    private String escolaridad;
    private String tituloObtenido;
    private String numeroColegiadoActivo;
    @Field(name = "idiomas_que_domina")
    private List<String> idiomasQueDomina;
    private String estudiaActualmente;
    private String nombreCarrera;
    private String otrosEstudiosRealizados;

    /*
     * @DBRef
     * private List<CertificadosEntity> certificados;
     */
    public int getEdad() {
        if (this.fechaDeNacimiento != null) {
            return Period.between(this.fechaDeNacimiento, LocalDate.now()).getYears();
        }
        return 0;
    }
}