package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Doctor;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.ConsultaDTO;
import com.springboot.sv.optica.repositories.CitaRepository;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import com.springboot.sv.optica.repositories.DoctorRepository;
import com.springboot.sv.optica.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public List<Consulta> findAll() {
        return (List<Consulta>) repository.findAll();
    }

    @Override
    public Optional<Consulta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Consulta> save(ConsultaDTO consultaDTO) {
        Optional<Cita> optionalCita = citaRepository.findById(consultaDTO.getCita());
        Optional<Doctor> optionalDoctor = doctorRepository.findById(consultaDTO.getDoctor());
        if(optionalCita.isPresent() && optionalDoctor.isPresent()){
            Cita citaDB = optionalCita.orElseThrow();
            Doctor doctorDB = optionalDoctor.orElseThrow();
            Consulta consulta = new Consulta();
            consulta.setCita(citaDB);
            consulta.setDoctor(doctorDB);
            consulta.setDiagnostico(consultaDTO.getDiagnostico());
            consulta.setInstrucciones(consultaDTO.getInstrucciones());
            return Optional.of(repository.save(consulta));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Consulta> update(Long id, ConsultaDTO consultaDTO) {
       Optional<Consulta> optionalConsulta = repository.findById(id);
       if(optionalConsulta.isPresent()){
           Optional<Cita> optionalCita = citaRepository.findById(consultaDTO.getCita());
           Optional<Doctor> optionalDoctor = doctorRepository.findById(consultaDTO.getDoctor());
           if(optionalCita.isPresent() && optionalDoctor.isPresent()){
               Cita citaDB = optionalCita.orElseThrow();
               Doctor doctorDB = optionalDoctor.orElseThrow();
               Consulta consultaDB = optionalConsulta.orElseThrow();
               consultaDB.setCita(citaDB);
               consultaDB.setDoctor(doctorDB);
               consultaDB.setDiagnostico(consultaDTO.getDiagnostico());
               consultaDB.setInstrucciones(consultaDTO.getInstrucciones());
               return Optional.of(repository.save(consultaDB));
           }
           return Optional.empty();
       }
       return  optionalConsulta;
    }

    @Override
    public Optional<Consulta> delete(Long id) {
        Optional<Consulta> optionalConsulta = repository.findById(id);
        optionalConsulta.ifPresent(consulta -> {
            //consulta.setCita(null);
            repository.delete(consulta);
        });
        return optionalConsulta;
    }
}
