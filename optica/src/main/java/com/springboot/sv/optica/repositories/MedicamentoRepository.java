package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Medicamento;
import org.springframework.data.repository.CrudRepository;

public interface MedicamentoRepository extends CrudRepository<Medicamento, Long> {
}
