package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Receta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RecetaRepository extends CrudRepository<Receta,Long> {

    boolean existsByMedicamentoIdAndId(Long medicamentoId, Long consultaId);

    // Verifica si la receta no esta asociada consulta y cita que este en una factura
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM Cita c " +
            "INNER JOIN c.consulta co " +
            "INNER JOIN co.recetas r " +
            "INNER JOIN r.medicamento m " +
            "INNER JOIN c.facturaCita fa " +
            "WHERE r.id = ?1")
    boolean ExisteRecetaFactura(Long id);
}
