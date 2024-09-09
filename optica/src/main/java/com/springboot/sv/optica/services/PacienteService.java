package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    Paciente save(Paciente paciente);

    Optional<Paciente> update(Long id, Paciente paciente);

    Optional<Paciente> delete(Long id);

}
