package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
    @Query("SELECT co " +
            "FROM Doctor d " +
            "INNER JOIN d.consultas co " +
            "WHERE d.id = ?1")
    List<Consulta> consultasDoctor(Long id);
}
