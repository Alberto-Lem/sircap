package com.cev.sircapcev.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.InscripcionesDTO;
import com.cev.sircapcev.entity.InscripcionesEntity;
import com.cev.sircapcev.entity.PersonasEntity;
import com.cev.sircapcev.entity.PlanesEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.InscripcionesRepository;
import com.cev.sircapcev.repositories.PersonasRepository;
import com.cev.sircapcev.repositories.PlanesRepository;

@Service
public class InscripcionesService {

        @Autowired
        InscripcionesRepository inscripcionRepository;
        @Autowired
        PersonasRepository personaRepository;
        @Autowired
        PlanesRepository planRepository;

        public List<InscripcionesEntity> getAll() {
                return inscripcionRepository.findAll();
        }

        public InscripcionesEntity getOne(String id) throws ResourceNotFoundException {
                return inscripcionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));
        }

        public InscripcionesEntity save(InscripcionesDTO dto) throws AttributeException, ResourceNotFoundException {
                if (inscripcionRepository.existsByNipAndNoPlan(dto.getNip(), dto.getNoPlan())) {
                        throw new AttributeException("Ya existe una inscripción con el mismo NIP y número de plan.");
                }
                PersonasEntity persona = personaRepository.findByNip(dto.getNip())
                                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada"));
                PlanesEntity plan = planRepository.findByNoPlan(dto.getNoPlan())
                                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));

                InscripcionesEntity inscripcion = new InscripcionesEntity();
                inscripcion.setFechaInscripcion(LocalDate.now());
                inscripcion.setHoraInscripcion(LocalTime.now());
                inscripcion.setHechoPor(SecurityContextHolder.getContext().getAuthentication().getName());
                inscripcion.setTipo(dto.getTipo());
                inscripcion.setEstado(dto.getEstado());
                inscripcion.setObservaciones(dto.getObservaciones());
                inscripcion.setNip(dto.getNip());
                inscripcion.setNoPlan(dto.getNoPlan());
                inscripcion.setPersona(persona);
                inscripcion.setPlan(plan);

                return inscripcionRepository.save(inscripcion);
        }

        public InscripcionesEntity update(String id, InscripcionesDTO dto)
                        throws ResourceNotFoundException, AttributeException {
                InscripcionesEntity inscripcion = inscripcionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));
                PersonasEntity persona = personaRepository.findByNip(dto.getNip())
                                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada"));
                PlanesEntity plan = planRepository.findByNoPlan(dto.getNoPlan())
                                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));
                inscripcion.setNip(dto.getNip());
                inscripcion.setTipo(dto.getTipo());
                inscripcion.setNoPlan(dto.getNoPlan());
                inscripcion.setFechaModificacion(LocalDate.now());
                inscripcion.setHoraModificacion(LocalTime.now());
                inscripcion.setUltimaModificacionPor(SecurityContextHolder.getContext().getAuthentication().getName());
                inscripcion.setEstado(dto.getEstado());
                inscripcion.setObservaciones(dto.getObservaciones());
                inscripcion.setPersona(persona);
                inscripcion.setPlan(plan);
                return inscripcionRepository.save(inscripcion);
        }

        public InscripcionesEntity delete(String id) throws ResourceNotFoundException {
                InscripcionesEntity inscripcion = inscripcionRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada"));
                inscripcionRepository.delete(inscripcion);
                return inscripcion;
        }
}
