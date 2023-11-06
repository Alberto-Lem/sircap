package com.cev.sircapcev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.CiclosDTO;
import com.cev.sircapcev.entity.CiclosEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.CiclosRepository;

@Service
public class CiclosService {

    @Autowired
    CiclosRepository cicloRepository;

    public List<CiclosEntity> getAll() {
        return cicloRepository.findAll();
    }

    public CiclosEntity getOne(String id) throws ResourceNotFoundException {
        return cicloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public CiclosEntity save(CiclosDTO dto) throws AttributeException {
        if (cicloRepository.existsByNombre(dto.getNombre()))
            throw new AttributeException("dpi already in use");
        CiclosEntity ciclo = new CiclosEntity(dto.getId(), dto.getNombre(), dto.getDescription());
        return cicloRepository.save(ciclo);
    }

    public CiclosEntity update(String id, CiclosDTO dto) throws ResourceNotFoundException, AttributeException {
        CiclosEntity ciclo = cicloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        if (cicloRepository.existsByNombre(dto.getNombre())
                && cicloRepository.findByNombre(dto.getNombre()).get().getId() != id)
            throw new AttributeException("dpi already in use");
        ciclo.setNombre(dto.getNombre());
        ciclo.setDescription(dto.getDescription());
        return cicloRepository.save(ciclo);
    }

    public CiclosEntity delete(String id) throws ResourceNotFoundException {
        CiclosEntity ciclo = cicloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ;
        cicloRepository.delete(ciclo);
        return ciclo;
    }
}
