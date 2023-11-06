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

import com.cev.sircapcev.DTO.ConstanciasCursoDTO;
import com.cev.sircapcev.entity.ConstanciasCursosEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.ConstanciasCursoService;

@RestController
@RequestMapping("/constanciaCursos")
@CrossOrigin
public class ConstanciasCursosController {
    @Autowired
    ConstanciasCursoService constanciaCursoService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<ConstanciasCursosEntity>> getAll() {
        return ResponseEntity.ok(constanciaCursoService.getAll());
    }

    @PreAuthorize("hasAnyAthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ConstanciasCursosEntity> getOne(@PathVariable("id") String id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(constanciaCursoService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody ConstanciasCursoDTO dto) throws AttributeException {
        ConstanciasCursosEntity constanciaCurso = constanciaCursoService.save(dto);
        String message = "ConstanciaCurso " + constanciaCurso.getNip() + " have been created";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody ConstanciasCursoDTO dto)
            throws ResourceNotFoundException, AttributeException {
        ConstanciasCursosEntity constanciaCurso = constanciaCursoService.update(id, dto);
        String message = "ConstanciaCurso " + constanciaCurso.getNip() + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        ConstanciasCursosEntity constanciaCurso = constanciaCursoService.delete(id);
        String message = "ConstanciaCurso " + constanciaCurso.getNip() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
