package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Consulta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends CrudRepository<Consulta, Long> {

    // Método para verificar si existe una consulta con una cita específica
    boolean existsByCitaId(Long citaId);

    // Método para verificar si una cita específica está asociada a una consulta específica
    boolean existsByCitaIdAndId(Long citaId, Long consultaId);

    Optional<Consulta> findByCitaId(Long citaId);

    //Verifica si la Consulta no esta asociada a una cita que este en una factura
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM Cita c " +
            "INNER JOIN c.consulta co " +
            "INNER JOIN c.facturaCita fa " +
            "WHERE co.id = ?1")
    boolean ExisteConsultaFactura(Long id);
}
