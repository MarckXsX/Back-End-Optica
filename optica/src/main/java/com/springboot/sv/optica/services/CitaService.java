package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.dto.CitaDTO;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    List<Cita> findAll();

    Optional<Cita> findById(Long id);

    Optional<Cita> save(CitaDTO citaDTO);

    Optional<Cita> update(Long id, CitaDTO citaDTO);

    Optional<Cita> delete(Long id);
}
