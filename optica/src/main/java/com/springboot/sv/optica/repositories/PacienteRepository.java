package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
}
