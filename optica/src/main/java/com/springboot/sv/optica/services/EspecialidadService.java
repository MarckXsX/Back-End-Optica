package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Especialidad;
import com.springboot.sv.optica.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    List<Especialidad> findAll();

    Optional<Especialidad> findById(Long id);

    Especialidad save(Especialidad especialidad);

    Optional<Especialidad> update(Long id, Especialidad especialidad);

    Optional<Especialidad> delete(Long id);
}
