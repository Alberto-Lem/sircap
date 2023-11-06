package com.cev.sircapcev.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.AsignaturasDTO;
import com.cev.sircapcev.entity.AsignaturasEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.AsignaturasRepository;

@Service
public class AsignaturasService {
    @Autowired
    AsignaturasRepository asignaturaRepository;

    public List<AsignaturasEntity> getAll() {
        return asignaturaRepository.findAll();
    }

    public AsignaturasEntity getOne(String id) throws ResourceNotFoundException {
        return asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public AsignaturasEntity save(AsignaturasDTO dto) throws AttributeException {
        LocalDateTime fechaCreacion = LocalDateTime.now();
        if (asignaturaRepository.existsByNombre(dto.getNombre()))
            throw new AttributeException("dpi already in use");
        AsignaturasEntity asignatura = new AsignaturasEntity(
                null,
                dto.getNombre(),
                dto.getCredito(),
                fechaCreacion,
                null,
                dto.getUsuario(),
                dto.getEstado(),
                dto.getNiveles());

        return asignaturaRepository.save(asignatura);
    }

    public AsignaturasEntity update(String id, AsignaturasDTO dto)
            throws ResourceNotFoundException, AttributeException {
        AsignaturasEntity asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        if (asignaturaRepository.existsByNombre(dto.getNombre())
                && asignaturaRepository.findByNombre(dto.getNombre()).get().getId() != id)
            throw new AttributeException("dpi already in use");
        asignatura.setNombre(dto.getNombre());
        asignatura.setCredito(dto.getCredito());
        asignatura.setFechaActualizacion(LocalDateTime.now());
        asignatura.setUsuario(dto.getUsuario());
        asignatura.setEstado(dto.getEstado());
        asignatura.setNiveles(dto.getNiveles());
        return asignaturaRepository.save(asignatura);
    }

    public AsignaturasEntity delete(String id) throws ResourceNotFoundException {
        AsignaturasEntity asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ;
        asignaturaRepository.delete(asignatura);
        return asignatura;
    }
}
