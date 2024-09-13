package com.springboot.sv.optica.services;



import com.springboot.sv.optica.entities.FacturaCita;
import com.springboot.sv.optica.entities.dto.FacturaCitaDTO;

import java.util.List;
import java.util.Optional;

public interface FacturaCitaService {

    List<FacturaCita> findAll();

    Optional<FacturaCita> findById(Long id);

    Optional<FacturaCita> save(FacturaCitaDTO facturaDTO);

    Optional<FacturaCita> update(Long id, FacturaCitaDTO facturaDTO);

    Optional<FacturaCita> delete(Long id);
}
