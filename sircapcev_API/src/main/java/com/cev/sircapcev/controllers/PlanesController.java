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

import com.cev.sircapcev.DTO.PlanesDTO;
import com.cev.sircapcev.entity.PlanesEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.PlanesService;

@RestController
@RequestMapping("/planes")
@CrossOrigin
public class PlanesController {
    @Autowired
    PlanesService planesService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<PlanesEntity>> getAll() {
        return ResponseEntity.ok(planesService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PlanesEntity> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(planesService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody PlanesDTO dto) throws AttributeException {
        PlanesEntity planes = planesService.savePlanes(dto);
        String message = "Id " + planes.getId() + "Numero del plan" + planes.getNoPlan() + " ha sido creado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody PlanesDTO dto)
            throws ResourceNotFoundException, AttributeException {
        PlanesEntity planes = planesService.update(id, dto);
        String message = "ID " + planes.getId() + " Planes " + planes.getNoPlan() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        PlanesEntity planes = planesService.delete(id);
        String message = "id" + planes.getId() + " Planes " + planes.getNoPlan() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
