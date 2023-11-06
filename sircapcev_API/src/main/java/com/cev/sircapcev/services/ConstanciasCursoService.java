package com.cev.sircapcev.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.ConstanciasCursoDTO;
import com.cev.sircapcev.entity.ConstanciasCursosEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.ConstanciasCursosRepository;

@Service
public class ConstanciasCursoService {
            @Autowired
    ConstanciasCursosRepository constanciaCursoRepository;

    public List<ConstanciasCursosEntity> getAll() {
        return constanciaCursoRepository.findAll();
    }

    public ConstanciasCursosEntity getOne(String id) throws ResourceNotFoundException {
        return constanciaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public ConstanciasCursosEntity save(ConstanciasCursoDTO dto) throws AttributeException {
        LocalDateTime fechaCreacion = LocalDateTime.now();
        if (constanciaCursoRepository.existsByNip(dto.getNip()))
            throw new AttributeException("dpi already in use");
        ConstanciasCursosEntity constanciaCurso = new ConstanciasCursosEntity(
            null,
            dto.getGradoPolicial(),
            dto.getNombres(),
            dto.getApellidos(),
            dto.getNip(),
            dto.getAsignaturas(),
            dto.getAprobacion(),
            fechaCreacion,
            null,
            dto.getDescripcion(),
            dto.getDocumentoRespaldo(),
            dto.getEstado(),
            dto.getUsuario()
            );
        return constanciaCursoRepository.save(constanciaCurso);
    }

    public ConstanciasCursosEntity update(String id, ConstanciasCursoDTO dto) throws ResourceNotFoundException, AttributeException {
        ConstanciasCursosEntity constanciaCurso = constanciaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        if (constanciaCursoRepository.existsByNip(dto.getNip())
                && constanciaCursoRepository.findByNip(dto.getNip()).get().getId() != id)
            throw new AttributeException("dpi already in use");
        constanciaCurso.setNip(dto.getNip());
        constanciaCurso.setGradoPolicial(dto.getGradoPolicial());
        constanciaCurso.setNombres(dto.getNombres());
        constanciaCurso.setApellidos(dto.getApellidos());
        constanciaCurso.setAsignaturas(dto.getAsignaturas());
        constanciaCurso.setAprobacion(dto.getAprobacion());
        constanciaCurso.setFechaModificacion(LocalDateTime.now());
        constanciaCurso.setDescripcion(dto.getDescripcion());
        constanciaCurso.setDocumentoRespaldo(dto.getDocumentoRespaldo());
        constanciaCurso.setEstado(dto.getEstado());
        constanciaCurso.setUsuario(dto.getUsuario());
        return constanciaCursoRepository.save(constanciaCurso);
    }

    public ConstanciasCursosEntity delete(String id) throws ResourceNotFoundException {
        ConstanciasCursosEntity constanciaCurso = constanciaCursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ;
        constanciaCursoRepository.delete(constanciaCurso);
        return constanciaCurso;
    }
}
