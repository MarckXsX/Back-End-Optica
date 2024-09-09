package com.springboot.sv.optica.services;


import com.springboot.sv.optica.entities.Receta;
import com.springboot.sv.optica.entities.dto.RecetaDTO;

import java.util.List;
import java.util.Optional;

public interface RecetaService {

    List<Receta> findAll();

    Optional<Receta> findById(Long id);

    Optional<Receta> save(RecetaDTO recetaDTO);

    Optional<Receta> update(Long id, RecetaDTO recetaDTO);

    Optional<Receta> delete(Long id);
}
