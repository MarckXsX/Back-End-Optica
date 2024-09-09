package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Expediente;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.ExpedienteDTO;
import com.springboot.sv.optica.repositories.ExpedienteRepository;
import com.springboot.sv.optica.repositories.PacienteRepository;
import com.springboot.sv.optica.services.ExpedienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteServiceImpl implements ExpedienteService {

    @Autowired
    private ExpedienteRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Expediente> findAll() {
        return (List<Expediente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Expediente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional()
    public Optional<Expediente> save(ExpedienteDTO expedienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(expedienteDTO.getPaciente());
        if(optionalPaciente.isPresent()){
            Paciente pacienteDB = optionalPaciente.orElseThrow();
            Expediente expediente = new Expediente();
            expediente.setPaciente(pacienteDB);
            expediente.setObservaciones(expedienteDTO.getObservaciones());
            expediente.setFecha_registro(expedienteDTO.getFecha_registro());

            return Optional.of(repository.save(expediente));
        }
        return Optional.empty();
    }

    @Override
    @Transactional()
    public Optional<Expediente> update(Long id, ExpedienteDTO expedienteDTO) {
        Optional<Expediente> optionalExpediente = repository.findById(id);
        if (optionalExpediente.isPresent()) {
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(expedienteDTO.getPaciente());
           if(optionalPaciente.isPresent()){
               Expediente expedienteDB = optionalExpediente.orElseThrow();
               Paciente pacienteDB = optionalPaciente.orElseThrow();
               expedienteDB.setPaciente(pacienteDB);
               expedienteDB.setObservaciones(expedienteDTO.getObservaciones());
               expedienteDB.setFecha_registro(expedienteDTO.getFecha_registro());

               return Optional.of(repository.save(expedienteDB));
           }
           return Optional.empty();
        }
        return optionalExpediente;
    }

    @Override
    @Transactional()
    public Optional<Expediente> delete(Long id) {
        Optional<Expediente> optionalExpediente = repository.findById(id);
        optionalExpediente.ifPresent(expediente -> {
            expediente.setPaciente(null);
            repository.delete(expediente);
        });
        return optionalExpediente;
    }
}
