package com.cev.sircapcev.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cev.sircapcev.DTO.AsignaturasDTO;
import com.cev.sircapcev.entity.AsignaturasEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.AsignaturasService;

@RestController
@RequestMapping("/asignaturas")
@CrossOrigin
public class AsignaturasController {
    @Autowired
    AsignaturasService asignaturaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<AsignaturasEntity>> getAll() {
        return ResponseEntity.ok(asignaturaService.getAll());
    }

    @PreAuthorize("hasAnyAthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturasEntity> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(asignaturaService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody AsignaturasDTO dto) throws AttributeException {
        AsignaturasEntity asignatura = asignaturaService.save(dto);
        String message = "Asignatura " + asignatura.getNombre() + " have been created";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody AsignaturasDTO dto)
            throws ResourceNotFoundException, AttributeException {
        AsignaturasEntity asignatura = asignaturaService.update(id, dto);
        String message = "Asignatura " + asignatura.getNombre() + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        AsignaturasEntity asignatura = asignaturaService.delete(id);
        String message = "Asignatura " + asignatura.getNombre() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
