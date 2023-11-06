package com.cev.sircapcev.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cev.sircapcev.DTO.InscripcionesDTO;
import com.cev.sircapcev.entity.InscripcionesEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.BitacorasService;
import com.cev.sircapcev.services.InscripcionesService;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin
public class InscripcionesController {
    @Autowired
    BitacorasService bitacorasService;

    @Autowired
    InscripcionesService inscripcionesService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<InscripcionesEntity>> getAll() {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        bitacorasService.historial(usuario, "Revisó el listado de inscripciones");
        return ResponseEntity.ok(inscripcionesService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionesEntity> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        InscripcionesEntity inscripcion = inscripcionesService.getOne(id);
        String actividad = "Revisó el detalle de la inscripcion con: " + id + " No. Plan: " + inscripcion.getNoPlan()
                + " NIP: " + inscripcion.getNip();
        bitacorasService.historial(usuario, actividad);
        return ResponseEntity.ok(inscripcionesService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody InscripcionesDTO dto)
            throws AttributeException, ResourceNotFoundException {
        InscripcionesEntity inscripciones = inscripcionesService.save(dto);
        String message = "inscripciones " + inscripciones.getNip() + " have been created";
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        bitacorasService.historial(usuario, "Incripcion creada: " + inscripciones.getId() + " No. Plan: "
                + inscripciones.getNoPlan() + " NIP: " + inscripciones.getNip());
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody InscripcionesDTO dto)
            throws ResourceNotFoundException, AttributeException {
        InscripcionesEntity inscripciones = inscripcionesService.update(id, dto);
        String message = "inscripciones " + inscripciones.getNip() + " have been updated";
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        bitacorasService.historial(usuario, "Incripcion modificada: " + inscripciones.getId() + " No. Plan: "
                + inscripciones.getNoPlan() + " NIP: " + inscripciones.getNip());
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        InscripcionesEntity inscripciones = inscripcionesService.delete(id);
        String message = "inscripciones " + inscripciones.getNip() + " have been deleted";
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        String actividad = "Eliminó los datos de la persona con id: " + id + " NIP: " + inscripciones.getNip()
                + "No. Plan" + inscripciones.getNoPlan();
        bitacorasService.historial(usuario, actividad);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
