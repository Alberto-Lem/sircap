package com.cev.sircapcev.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cev.sircapcev.DTO.ConteoDatosDTO;
import com.cev.sircapcev.DTO.FiltroAnioDTO;
import com.cev.sircapcev.DTO.FiltroFechaDTO;
import com.cev.sircapcev.DTO.PlanConConteoDTO;
import com.cev.sircapcev.services.BitacorasService;
import com.cev.sircapcev.services.ProcesamientoDatosService;

@RestController
@RequestMapping("/procesamientoDatos")
@CrossOrigin
public class ProcesamientoDatosController {
    @Autowired
    BitacorasService bitacorasService;

    @Autowired
    ProcesamientoDatosService procesamientoDatosService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/planes_generos_anio") // Cambiado a POST
    public ResponseEntity<List<PlanConConteoDTO>> porPlanesGeneroAnio(@RequestBody FiltroAnioDTO filtro) {
        try {
            Integer anioInicio = filtro.getAnioInicio();
            Integer anioFin = filtro.getAnioFin();
            List<PlanConConteoDTO> datosProcesados = procesamientoDatosService.porPlanesGeneroAnio(anioInicio, anioFin);
            String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            bitacorasService.historial(usuario, "Procesó datos para gráficas");
            return new ResponseEntity<>(datosProcesados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/conteo_por_anio")
    public ResponseEntity<List<ConteoDatosDTO>> conteoPorAnio(@RequestBody FiltroAnioDTO filtro) {
        try {
            Integer anioInicio = filtro.getAnioInicio();
            Integer anioFin = filtro.getAnioFin();
            List<ConteoDatosDTO> datosProcesados = procesamientoDatosService.conteoPorAnio(anioInicio, anioFin);
            String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            bitacorasService.historial(usuario, "Procesó datos para gráficas de conteo por año");
            return new ResponseEntity<>(datosProcesados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/conteo_por_genero")
    public ResponseEntity<List<ConteoDatosDTO>> conteoPorGenero() {
        try {
            List<ConteoDatosDTO> datosProcesados = procesamientoDatosService.conteoPorGenero();
            String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            bitacorasService.historial(usuario, "Procesó datos para gráficas de conteo por género");
            return new ResponseEntity<>(datosProcesados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/conteo_grado_anio")
    public ResponseEntity<List<PlanConConteoDTO>> conteoPorGradoPolicial(@RequestBody FiltroFechaDTO filtro) {
        try {
            LocalDate fechaInicio = filtro.getFechaInicio();
            LocalDate fechaFin = filtro.getFechaFin();
            List<PlanConConteoDTO> datosProcesados = procesamientoDatosService.conteoGradoPorAnio(fechaInicio,
                    fechaFin);
            return new ResponseEntity<>(datosProcesados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
