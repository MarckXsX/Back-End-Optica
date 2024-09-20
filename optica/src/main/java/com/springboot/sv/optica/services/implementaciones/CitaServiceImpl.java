package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.repositories.CitaRepository;
import com.springboot.sv.optica.repositories.PacienteRepository;
import com.springboot.sv.optica.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<Cita> findAll() {
        return (List<Cita>) repository.findAll();
    }

    @Override
    public Optional<Cita> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Cita> save(CitaDTO citaDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(citaDTO.getPaciente());
        if(optionalPaciente.isPresent() && !citaDTO.getEstado().equalsIgnoreCase("Finalizada")){
            Paciente pacienteDB = optionalPaciente.orElseThrow();
            Cita cita = new Cita();
            cita.setPaciente(pacienteDB);
            cita.setFecha_cita(citaDTO.getFecha_cita());
            cita.setHora_cita(citaDTO.getHora_cita());
            cita.setEstado(citaDTO.getEstado());
            cita.setCosto(citaDTO.getCosto());

            return Optional.of(repository.save(cita));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cita> update(Long id, CitaDTO citaDTO) {
        Optional<Cita> optionalCita = repository.findById(id);
        if (optionalCita.isPresent()) {
            Cita citaDB = optionalCita.orElseThrow();
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(citaDTO.getPaciente());
            if(optionalPaciente.isPresent() &&  citaDB.getFacturaCita() == null){
                Paciente pacienteDB = optionalPaciente.orElseThrow();
                citaDB.setPaciente(pacienteDB);
                citaDB.setFecha_cita(citaDTO.getFecha_cita());
                citaDB.setHora_cita(citaDTO.getHora_cita());
                citaDB.setEstado(citaDTO.getEstado());
                citaDB.setCosto(citaDTO.getCosto());

                return Optional.of(repository.save(citaDB));
            }
            return Optional.empty();
        }
        return optionalCita;
    }

    @Override
    public Optional<Cita> delete(Long id) {
        Optional<Cita> optionalCita = repository.findById(id);
        if(optionalCita.isPresent()){
            Cita citaDB = optionalCita.orElseThrow();
            if(citaDB.getFacturaCita() == null && citaDB.getConsulta() == null) {
                repository.delete(citaDB);
                return optionalCita;
            }
            return Optional.empty();
        }
        return optionalCita;
    }
}
