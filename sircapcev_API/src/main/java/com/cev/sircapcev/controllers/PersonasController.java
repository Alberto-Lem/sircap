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

import com.cev.sircapcev.DTO.PersonasDTO;
import com.cev.sircapcev.entity.PersonasEntity;
import com.cev.sircapcev.global.dto.MessageDto;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.services.BitacorasService;
import com.cev.sircapcev.services.PersonasService;

@RestController
@RequestMapping("/personas")
@CrossOrigin
public class PersonasController {

    @Autowired
    BitacorasService bitacorasService;

    @Autowired
    PersonasService personaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<PersonasEntity>> getAll() {
        String usuario= SecurityContextHolder.getContext().getAuthentication().getName();
        bitacorasService.historial(usuario, "Revisó el listado de personas");
        return ResponseEntity.ok(personaService.getAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PersonasEntity> getOne(@PathVariable("id") String id) throws ResourceNotFoundException {
        String usuario= SecurityContextHolder.getContext().getAuthentication().getName();
        PersonasEntity persona = personaService.getOne(id);
        String actividad= "Revisó la información de la persona con id: " + id + " Nombres: " + persona.getNombres() + " NIP: " + persona.getNip();
        bitacorasService.historial(usuario, actividad);
        return ResponseEntity.ok(personaService.getOne(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/findByNip/{nip}")
    public ResponseEntity<PersonasEntity> findByNip(@PathVariable("nip") String nip)
            throws ResourceNotFoundException {
        PersonasEntity persona = personaService.findByNip(nip);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            throw new ResourceNotFoundException("Persona not found with NIP: " + nip);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> save(@Valid @RequestBody PersonasDTO dto) throws AttributeException {
        PersonasEntity persona = personaService.save(dto);
        String message = "Persona " + persona.getDpi() + " have been created";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") String id, @Valid @RequestBody PersonasDTO dto)
            throws ResourceNotFoundException, AttributeException {
        PersonasEntity persona = personaService.update(id, dto);
        String message = "Persona " + persona.getDpi() + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") String id) throws ResourceNotFoundException {
        PersonasEntity persona = personaService.delete(id);
        String message = "Persona " + persona.getDpi() + " have been deleted";
        String usuario= SecurityContextHolder.getContext().getAuthentication().getName();
        String actividad= "Eliminó los datos de la persona con id: " + id + " Nombres: " + persona.getNombres() + " NIP: " + persona.getNip();
        bitacorasService.historial(usuario, actividad);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
