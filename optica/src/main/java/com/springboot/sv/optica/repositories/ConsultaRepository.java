package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Consulta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

    // Método para verificar si existe una consulta con una cita específica
    boolean existsByCitaId(Long citaId);

    // Método para verificar si una cita específica está asociada a una consulta específica
    boolean existsByCitaIdAndId(Long citaId, Long consultaId);

    Optional<Consulta> findByCitaId(Long citaId);
}
