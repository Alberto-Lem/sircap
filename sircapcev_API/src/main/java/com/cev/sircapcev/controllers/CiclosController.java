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

import com.cev.sircapcev.DTO.CiclosDTO;
import com.cev.sircapcev.entity.CiclosEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.CiclosService;

@RestController
@RequestMapping("/ciclos")
@CrossOrigin
public class CiclosController {
    @Autowired
    CiclosService cicloService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<CiclosEntity>> getAll() {
        return ResponseEntity.ok(cicloService.getAll());
    }

    @PreAuthorize("hasAnyAthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CiclosEntity> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(cicloService.getOne(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody CiclosDTO dto) throws AttributeException {
        CiclosEntity ciclo = cicloService.save(dto);
        String message = "Ciclo " + ciclo.getNombre() + " have been created";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody CiclosDTO dto)
            throws ResourceNotFoundException, AttributeException {
        CiclosEntity ciclo = cicloService.update(id, dto);
        String message = "Ciclo " + ciclo.getNombre() + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        CiclosEntity ciclo = cicloService.delete(id);
        String message = "Ciclo " + ciclo.getNombre() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
