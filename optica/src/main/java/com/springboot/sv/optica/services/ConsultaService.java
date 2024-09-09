package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.dto.ConsultaDTO;

import java.util.List;
import java.util.Optional;

public interface ConsultaService {

    List<Consulta> findAll();

    Optional<Consulta> findById(Long id);

    Optional<Consulta> save(ConsultaDTO consultaDTO);

    Optional<Consulta> update(Long id, ConsultaDTO consultaDTO);

    Optional<Consulta> delete(Long id);
}
