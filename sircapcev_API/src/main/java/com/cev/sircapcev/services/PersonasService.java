package com.cev.sircapcev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.PersonasDTO;
import com.cev.sircapcev.entity.PersonasEntity;
import com.cev.sircapcev.global.exceptions.AttributeException;
import com.cev.sircapcev.global.exceptions.ResourceNotFoundException;
import com.cev.sircapcev.repositories.PersonasRepository;

@Service
public class PersonasService {

    @Autowired
    PersonasRepository personaRepository;

    public List<PersonasEntity> getAll() {
        return personaRepository.findAll();
    }

    public PersonasEntity getOne(String id) throws ResourceNotFoundException {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    public PersonasEntity findByNip(String nip) throws ResourceNotFoundException {
        return personaRepository.findByNip(nip)
                .orElseThrow(() -> new ResourceNotFoundException("Persona not found with NIP: " + nip));
    }

    public PersonasEntity save(PersonasDTO dto) throws AttributeException {
        if (personaRepository.existsByDpi(dto.getDpi()))
            throw new AttributeException("DPI exisente en este sistema");
        PersonasEntity persona = new PersonasEntity(
                null,
                dto.getNip(),
                dto.getGradoPolicial(),
                dto.getNombres(),
                dto.getApellidos(),
                dto.getGenero(),
                dto.getDpi(),
                dto.getNit(),
                dto.getNoAfiliacionIgss(),
                dto.getRenglonPresupuestario(),
                dto.getFechaDeNacimiento(),
                dto.getDireccionResidencial(),
                dto.getLugarDeServicio(),
                dto.getDestinoActual(),
                dto.getDependenciaDondeLabora(),
                dto.getTelefono(),
                dto.getCorreoElectronicoPersonal(),
                dto.getCorreoElectronicoInstitucional(),
                dto.getGrupoEtnico(),
                dto.getEscolaridad(),
                dto.getTituloObtenido(),
                dto.getNumeroColegiadoActivo(),
                dto.getIdiomasQueDomina(),
                dto.getEstudiaActualmente(),
                dto.getNombreCarrera(),
                dto.getOtrosEstudiosRealizados());

        return personaRepository.save(persona);
    }

    public PersonasEntity update(String id, PersonasDTO dto) throws ResourceNotFoundException, AttributeException {
        PersonasEntity persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
       /*  if (personaRepository.existsByDpi(dto.getDpi())
                && personaRepository.findByDpi(dto.getDpi()).get().getId() != id)
            throw new AttributeException("dpi already in use"); */
        persona.setNip(dto.getNip());
        persona.setGradoPolicial(dto.getGradoPolicial());
        persona.setNombres(dto.getNombres());
        persona.setApellidos(dto.getApellidos());
        persona.setGenero(dto.getGenero());
        persona.setDpi(dto.getDpi());
        persona.setNit(dto.getNit());
        persona.setNoAfiliacionIgss(dto.getNoAfiliacionIgss());
        persona.setRenglonPresupuestario(dto.getRenglonPresupuestario());
        persona.setFechaDeNacimiento(dto.getFechaDeNacimiento());
        persona.setDireccionResidencial(dto.getDireccionResidencial());
        persona.setLugarDeServicio(dto.getLugarDeServicio());
        persona.setDestinoActual(dto.getDestinoActual());
        persona.setDependenciaDondeLabora(dto.getDependenciaDondeLabora());
        persona.setTelefono(dto.getTelefono());
        persona.setCorreoElectronicoPersonal(dto.getCorreoElectronicoPersonal());
        persona.setCorreoElectronicoInstitucional(dto.getCorreoElectronicoInstitucional());
        persona.setGrupoEtnico(dto.getGrupoEtnico());
        persona.setEscolaridad(dto.getEscolaridad());
        persona.setTituloObtenido(dto.getTituloObtenido());
        persona.setNumeroColegiadoActivo(dto.getNumeroColegiadoActivo());
        persona.setIdiomasQueDomina(dto.getIdiomasQueDomina());
        persona.setEstudiaActualmente(dto.getEstudiaActualmente());
        persona.setNombreCarrera(dto.getNombreCarrera());
        persona.setOtrosEstudiosRealizados(dto.getOtrosEstudiosRealizados());

        return personaRepository.save(persona);
    }

    public PersonasEntity delete(String id) throws ResourceNotFoundException {
        PersonasEntity persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        ;
        personaRepository.delete(persona);
        return persona;
    }

}
