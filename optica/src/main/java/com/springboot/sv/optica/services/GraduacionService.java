package com.springboot.sv.optica.services;


import com.springboot.sv.optica.entities.Graduacion;
import com.springboot.sv.optica.entities.dto.GraduacionDTO;

import java.util.List;
import java.util.Optional;

public interface GraduacionService {

    List<Graduacion> findAll();

    Optional<Graduacion> findById(Long id);

    Optional<Graduacion> save(GraduacionDTO graduacionDTO);

    Optional<Graduacion> update(Long id, GraduacionDTO graduacionDTO);

    Optional<Graduacion> delete(Long id);
}
