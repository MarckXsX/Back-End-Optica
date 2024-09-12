package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Aro;

import java.util.List;
import java.util.Optional;

public interface AroService {

    List<Aro> findAll();

    Optional<Aro> findById(Long id);

    Aro save(Aro aro);

    Optional<Aro> update(Long id, Aro aro);

    Optional<Aro> delete(Long id);
}
