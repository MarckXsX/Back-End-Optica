package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Expediente;
import com.springboot.sv.optica.entities.dto.ExpedienteDTO;

import java.util.List;
import java.util.Optional;

public interface ExpedienteService {
    List<Expediente> findAll();

    Optional<Expediente> findById(Long id);

    Optional<Expediente> save(ExpedienteDTO expediente);

    Optional<Expediente> update(Long id, ExpedienteDTO expedienteDTO);

    Optional<Expediente> delete(Long id);
}
