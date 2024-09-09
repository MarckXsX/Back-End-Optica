package com.springboot.sv.optica.services;



import com.springboot.sv.optica.entities.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {

    List<Medicamento> findAll();

    Optional<Medicamento> findById(Long id);

    Medicamento save(Medicamento medicamento);

    Optional<Medicamento> update(Long id, Medicamento medicamento);

    Optional<Medicamento> delete(Long id);
}
