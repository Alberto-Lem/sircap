package com.cev.sircapcev.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cev.sircapcev.DTO.ConteoDatosDTO;
import com.cev.sircapcev.DTO.PlanConConteoDTO;
import com.cev.sircapcev.entity.InscripcionesEntity;
import com.cev.sircapcev.repositories.InscripcionesRepository;
import com.cev.sircapcev.repositories.PersonasRepository;
import com.cev.sircapcev.repositories.PlanesRepository;

@Service
public class ProcesamientoDatosService {

    @Autowired
    InscripcionesRepository inscripcionRepository;
    @Autowired
    PersonasRepository personaRepository;
    @Autowired
    PlanesRepository planRepository;

    public List<PlanConConteoDTO> porPlanesGeneroAnio(Integer anoInicio, Integer anoFin) {
        List<InscripcionesEntity> todasLasInscripciones = inscripcionRepository.findAll();
        Map<String, Map<String, Integer>> conteoPorPlanYGenero = new HashMap<>();

        for (InscripcionesEntity inscripcion : todasLasInscripciones) {
            String plan = inscripcion.getNoPlan();
            Integer anoPlan = inscripcion.getPlan().getFechaInicio().getYear(); // Asegúrate de tener este campo en tu
            if ((anoInicio != null && anoPlan < anoInicio) || (anoFin != null && anoPlan > anoFin)) {
                continue;
            }

            String genero = inscripcion.getPersona().getGenero();
            Map<String, Integer> conteoPorGenero = conteoPorPlanYGenero.getOrDefault(plan, new HashMap<>());
            conteoPorGenero.put(genero, conteoPorGenero.getOrDefault(genero, 0) + 1);
            conteoPorPlanYGenero.put(plan, conteoPorGenero);
        }

        List<PlanConConteoDTO> resultado = new ArrayList<>();
        for (Map.Entry<String, Map<String, Integer>> entry : conteoPorPlanYGenero.entrySet()) {
            PlanConConteoDTO planConConteo = new PlanConConteoDTO();
            planConConteo.setName(entry.getKey());

            List<ConteoDatosDTO> series = new ArrayList<>();
            for (Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
                series.add(new ConteoDatosDTO(subEntry.getKey(), subEntry.getValue()));
            }

            Collections.sort(series, new Comparator<ConteoDatosDTO>() {
                @Override
                public int compare(ConteoDatosDTO c1, ConteoDatosDTO c2) {
                    if ("Masculino".equals(c1.getName()) && "Femenino".equals(c2.getName())) {
                        return -1;
                    } else if ("Femenino".equals(c1.getName()) && "Masculino".equals(c2.getName())) {
                        return 1;
                    }
                    return 0;
                }
            });

            planConConteo.setSeries(series);
            resultado.add(planConConteo);
        }

        Collections.sort(resultado, new Comparator<PlanConConteoDTO>() {
            @Override
            public int compare(PlanConConteoDTO p1, PlanConConteoDTO p2) {
                String name1 = p1.getName();
                String name2 = p2.getName();
                Integer year1 = Integer.parseInt(name1.substring(name1.lastIndexOf("-") + 1));
                Integer year2 = Integer.parseInt(name2.substring(name2.lastIndexOf("-") + 1));
                return year1.compareTo(year2);
            }
        });
        

        return resultado;
    }

    public List<ConteoDatosDTO> conteoPorAnio(Integer anoInicio, Integer anoFin) {
        List<InscripcionesEntity> todasLasInscripciones = inscripcionRepository.findAll();
        Map<Integer, Integer> conteoPorAno = new HashMap<>();

        todasLasInscripciones.forEach(inscripcion -> {
            Integer anoPlan = inscripcion.getPlan().getFechaInicio().getYear();
            if ((anoInicio != null && anoPlan < anoInicio) || (anoFin != null && anoPlan > anoFin)) {
                return;
            }
            conteoPorAno.merge(anoPlan, 1, (a, b) -> a + b);
        });

        return conteoPorAno.entrySet().stream()
                .map(entry -> new ConteoDatosDTO("Año " + entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<ConteoDatosDTO> conteoPorGenero() {
        List<InscripcionesEntity> todasLasInscripciones = inscripcionRepository.findAll();
        Map<String, Integer> conteoPorGenero = new HashMap<>();

        todasLasInscripciones.forEach(inscripcion -> {
            String genero = inscripcion.getPersona().getGenero();
            conteoPorGenero.merge(genero, 1, (a, b) -> a + b);
        });

        return conteoPorGenero.entrySet().stream()
                .map(entry -> new ConteoDatosDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<PlanConConteoDTO> conteoGradoPorAnio(LocalDate fechaInicio, LocalDate fechaFin) {
        List<InscripcionesEntity> todasLasInscripciones = inscripcionRepository.findAll();
        Map<String, Map<Integer, Integer>> conteoPorGradoPolicialYAno = new HashMap<>();
    
        int yearInicio = (fechaInicio != null) ? fechaInicio.getYear() : 0;
        int yearFin = (fechaFin != null) ? fechaFin.getYear() : 0;
    
        for (InscripcionesEntity inscripcion : todasLasInscripciones) {
            LocalDate fechaPlan = inscripcion.getPlan().getFechaInicio();
            if ((fechaInicio != null && fechaPlan.isBefore(fechaInicio)) || (fechaFin != null && fechaPlan.isAfter(fechaFin))) {
                continue;
            }
    
            String gradoPolicial = inscripcion.getPersona().getGradoPolicial();
            Integer anoPlan = fechaPlan.getYear();
    
            Map<Integer, Integer> conteoPorAno = conteoPorGradoPolicialYAno.getOrDefault(gradoPolicial, new HashMap<>());
    
            for (int year = yearInicio; year <= yearFin; year++) {
                conteoPorAno.putIfAbsent(year, 0);
            }
    
            conteoPorAno.put(anoPlan, conteoPorAno.getOrDefault(anoPlan, 0) + 1);
            conteoPorGradoPolicialYAno.put(gradoPolicial, conteoPorAno);
        }
    
        List<String> gradosOrdenados = Arrays.asList(
                "Administrativo", "Agente de Policía", "Subinspector de Policía",
                "Inspector de Policía", "Oficial Tercero de Policía", "Oficial Segundo de Policía",
                "Oficial Primero de Policía", "Subcomisario de Policía",
                "Comisario de Policía", "Comisario General de Policía");
    
        List<PlanConConteoDTO> resultado = new ArrayList<>();
    
        for (String grado : gradosOrdenados) {
            PlanConConteoDTO gradoConConteo = new PlanConConteoDTO();
            gradoConConteo.setName(grado);
    
            Map<Integer, Integer> conteoPorAno = conteoPorGradoPolicialYAno.getOrDefault(grado, new HashMap<>());
            List<ConteoDatosDTO> series = new ArrayList<>();
    
            for (int year = yearInicio; year <= yearFin; year++) {
                int valor = conteoPorAno.getOrDefault(year, 0);
                series.add(new ConteoDatosDTO(String.valueOf(year), valor));
            }
    
            Collections.sort(series, Comparator.comparing(ConteoDatosDTO::getName));
            gradoConConteo.setSeries(series);
            resultado.add(gradoConConteo);
        }
    
        return resultado;
    }
    
}
