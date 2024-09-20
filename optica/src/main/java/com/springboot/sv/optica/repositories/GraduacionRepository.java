package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Graduacion;
import org.springframework.data.repository.CrudRepository;

public interface GraduacionRepository extends CrudRepository<Graduacion, Long> {
    //verifica que graduacion exista con la consulta proporcionada
    boolean existsByConsultaIdAndId(Long consultaId, Long id);

    boolean existsByConsultaId(Long id);
}
