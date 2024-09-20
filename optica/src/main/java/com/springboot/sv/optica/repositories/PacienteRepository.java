package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    @Query("SELECT ci " +
            "FROM Paciente p " +
            "INNER JOIN p.citas ci " +
            "WHERE p.id = ?1")
    List<Cita> citasPaciente(Long id);

    @Query("SELECT co " +
            "FROM Paciente p " +
            "INNER JOIN p.citas ci " +
            "INNER JOIN ci.consulta co " +
            "WHERE p.id = ?1")
    List<Consulta> consultasPaciente(Long id);

    @Query("SELECT  fc " +
            "FROM Paciente p " +
            "INNER JOIN p.facturaCitaList fc " +
            "WHERE p.id = ?1")
    List<FacturaCita> facturasCitasPaciente(Long id);

    @Query("SELECT  fp " +
            "FROM Paciente p " +
            "INNER JOIN p.facturaProductoList fp " +
            "WHERE p.id = ?1")
    List<FacturaProducto> facturasLentesPaciente(Long id);


}
