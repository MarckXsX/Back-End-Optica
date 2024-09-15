package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Expediente;
import org.springframework.data.repository.CrudRepository;

public interface ExpedienteRepository extends CrudRepository<Expediente, Long> {

    //Verifica si existe un Expediente con el id del paciente
    boolean existsByPacienteId(Long pacienteId);

    //Verifica que existe un registro de un expediente por medio de su id y id del paciente
    boolean existsByPacienteIdAndId(Long pacienteId, Long expediente);
}
