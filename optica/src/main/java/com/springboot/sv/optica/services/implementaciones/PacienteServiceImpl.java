package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.repositories.PacienteRepository;
import com.springboot.sv.optica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return (List<Paciente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Paciente save(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    @Transactional()
    public Optional<Paciente> update(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if (optionalPaciente.isPresent()) {
            Paciente pacienteDB = optionalPaciente.orElseThrow();
            pacienteDB.setNombres(paciente.getNombres());
            pacienteDB.setApellidos(paciente.getApellidos());
            pacienteDB.setFecha_nacimiento(paciente.getFecha_nacimiento());
            pacienteDB.setTelefono(paciente.getTelefono());
            pacienteDB.setCorreo(paciente.getCorreo());

            return Optional.of(repository.save(pacienteDB));
        }

        return optionalPaciente;
    }

    @Override
    @Transactional()
    public Optional<Paciente> delete(Long id) {
        Optional<Paciente> optionalPaciente = repository.findById(id);
        optionalPaciente.ifPresent(paciente -> {
            repository.delete(paciente);
        });
        return optionalPaciente;
    }
}