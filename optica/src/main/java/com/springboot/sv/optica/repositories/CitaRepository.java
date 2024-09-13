package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Objects;

public interface CitaRepository extends CrudRepository<Cita,Long> {

    @Query("SELECT c.costo, m.id, m.costo, r.cantidad " +
            "FROM Cita c " +
            "INNER JOIN c.consulta co " +
            "INNER JOIN co.recetas r " +
            "INNER JOIN r.medicamento m " +
            "WHERE c.id = ?1")
    List<Object[]> obtenerMedicamentosCita(Long id);
}
