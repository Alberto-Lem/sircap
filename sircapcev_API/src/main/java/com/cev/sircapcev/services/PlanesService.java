package com.cev.sircapcev.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.PlanesDTO;
import com.cev.sircapcev.entity.PlanesEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.PlanesRepository;

@Service
public class PlanesService {

    @Autowired
    PlanesRepository planesRepository;
    @Autowired
    ImagesStorageService imageStorageService;

    public List<PlanesEntity> getAll() {
        return planesRepository.findAll();
    }

    public PlanesEntity getOne(String id) throws ResourceNotFoundException {
        return planesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public PlanesEntity savePlanes(PlanesDTO dto) throws AttributeException {
        LocalDate fechaCreacion = LocalDate.now();

        if (planesRepository.existsByNoPlan(dto.getNoPlan()))
            throw new AttributeException("El número de plan ya está en uso");

        PlanesEntity planes = new PlanesEntity(
                null,
                dto.getNoPlan(),
                dto.getNombrePlan(),
                dto.getCantidadParticipante(),
                dto.getEstado(),
                fechaCreacion,
                null,
                dto.getFechaInicio(),
                dto.getFechaFinalizacion(),
                dto.getModalidad(),
                dto.getDirigidoA(),
                dto.getEncargado(),
                dto.getLugarEjecucion(),
                dto.getComentario());

        return planesRepository.save(planes);
    }

    public PlanesEntity update(String id, PlanesDTO dto) throws ResourceNotFoundException, AttributeException {
        PlanesEntity planes = planesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planes not found"));

        // Actualizar los campos
        planes.setNoPlan(dto.getNoPlan());
        planes.setNombrePlan(dto.getNombrePlan());
        planes.setCantidadPerticipante(dto.getCantidadParticipante());
        planes.setEstado(dto.getEstado());
        planes.setFechaModificacion(LocalDate.now());
        planes.setFechaInicio(dto.getFechaInicio());
        planes.setFechaFinalizacion(dto.getFechaFinalizacion());
        planes.setModalidad(dto.getModalidad());
        planes.setDirigidoA(dto.getDirigidoA());
        planes.setEncargado(dto.getEncargado());
        planes.setLugarEjecucion(dto.getLugarEjecucion());
        planes.setComentario(dto.getComentario());
        return planesRepository.save(planes);
    }

    public PlanesEntity delete(String id) throws ResourceNotFoundException {
        PlanesEntity planes = planesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ;
        planesRepository.delete(planes);
        return planes;
    }
}
