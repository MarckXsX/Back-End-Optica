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
            if(citaDB.getEstado().equalsIgnoreCase("Pendiente")){
                citaDB.setEstado("Finalizada");
                citaRepository.save(citaDB);

                Consulta consulta = new Consulta();
                consulta.setCita(citaDB);
                consulta.setDoctor(doctorDB);
                consulta.setDiagnostico(consultaDTO.getDiagnostico());
                consulta.setInstrucciones(consultaDTO.getInstrucciones());
                return Optional.of(repository.save(consulta));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Consulta> update(Long id, ConsultaDTO consultaDTO) {
       Optional<Consulta> optionalConsulta = repository.findById(id);

       if(optionalConsulta.isPresent() ){
           boolean ConsultaFactura = repository.ExisteConsultaFactura(id);

           if(ConsultaFactura){
               return Optional.empty();
           }

           Optional<Cita> optionalCita = citaRepository.findById(consultaDTO.getCita());
           Optional<Doctor> optionalDoctor = doctorRepository.findById(consultaDTO.getDoctor());

           boolean citaConsulta = repository.existsByCitaIdAndId(consultaDTO.getCita(), id);

           boolean citaSinConsulta = repository.existsByCitaId(consultaDTO.getCita());

           Cita citaDB = optionalCita.orElseThrow();
           Doctor doctorDB = optionalDoctor.orElseThrow();
           Consulta consultaDB = optionalConsulta.orElseThrow();

               if(citaConsulta) { //Si cita es la misma para la consulta
                   consultaDB.setCita(citaDB);
                   consultaDB.setDoctor(doctorDB);
                   consultaDB.setDiagnostico(consultaDTO.getDiagnostico());
                   consultaDB.setInstrucciones(consultaDTO.getInstrucciones());
                   return Optional.of(repository.save(consultaDB));
               } else if (!citaSinConsulta /*|| citaDB.getConsulta() == null*/) { //si la cita cambia para la consulta y no esta registrada en otra consulta
                  Cita citaAnt = consultaDB.getCita();
                  citaAnt.setEstado("Pendiente");
                  citaRepository.save(citaAnt);

                  citaDB.setEstado("Finalizada");
                  citaRepository.save(citaDB);

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
