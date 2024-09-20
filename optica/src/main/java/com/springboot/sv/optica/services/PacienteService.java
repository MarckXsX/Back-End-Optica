package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.*;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    List<Cita> citasPaciente(Long id);

    List<Consulta> consultasPaciente(Long id);

    List<FacturaCita> facturasCitasPaciente(Long id);

    List<FacturaProducto> facturasLentesPaciente(Long id);

    Paciente save(Paciente paciente);

    Optional<Paciente> update(Long id, Paciente paciente);

    Optional<Paciente> delete(Long id);

}
