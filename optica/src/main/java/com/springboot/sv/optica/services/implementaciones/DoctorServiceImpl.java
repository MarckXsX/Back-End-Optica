package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.*;
import com.springboot.sv.optica.entities.dto.DoctorDTO;
import com.springboot.sv.optica.repositories.DoctorRepository;
import com.springboot.sv.optica.repositories.EspecialidadRepository;
import com.springboot.sv.optica.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private EspecialidadRepository especialidadRepository;


    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) repository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Consulta> consultasDoctor(Long id) {
        return repository.consultasDoctor(id);
    }

    @Override
    public Optional<Doctor> save(DoctorDTO doctorDTO) {
        Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(doctorDTO.getEspecialidad());
        if(optionalEspecialidad.isPresent()){
            Especialidad especialidadDB = optionalEspecialidad.orElseThrow();
            Doctor doctor = new Doctor();
            doctor.setEspecialidad(especialidadDB);
            doctor.setNombres(doctorDTO.getNombres());
            doctor.setApellidos(doctorDTO.getApellidos());
            doctor.setTelefono(doctorDTO.getTelefono());
            doctor.setCorreo(doctorDTO.getCorreo());
            doctor.setLicencia(doctorDTO.getLicencia());

            return Optional.of(repository.save(doctor));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Doctor> update(Long id, DoctorDTO doctorDTO) {
        Optional<Doctor> optionalDoctor = repository.findById(id);
        if (optionalDoctor.isPresent()) {
            Optional<Especialidad> optionalEspecialidad = especialidadRepository.findById(doctorDTO.getEspecialidad());
            if(optionalEspecialidad.isPresent()){
                Especialidad especialidadDB = optionalEspecialidad.orElseThrow();
                Doctor doctorDB = optionalDoctor.orElseThrow();
                doctorDB.setEspecialidad(especialidadDB);
                doctorDB.setNombres(doctorDTO.getNombres());
                doctorDB.setApellidos(doctorDTO.getApellidos());
                doctorDB.setTelefono(doctorDTO.getTelefono());
                doctorDB.setCorreo(doctorDTO.getCorreo());
                doctorDB.setLicencia(doctorDTO.getLicencia());

                return Optional.of(repository.save(doctorDB));
            }
            return Optional.empty();
        }
        return optionalDoctor;
    }

    @Override
    public Optional<Doctor> delete(Long id) {
        Optional<Doctor> optionalDoctor = repository.findById(id);
        if(optionalDoctor.isPresent()){
            Doctor doctorDB = optionalDoctor.orElseThrow();
            if(doctorDB.getConsultas().isEmpty()){
                repository.delete(doctorDB);
                return optionalDoctor;
            }
            return Optional.empty();
        }
        return optionalDoctor;
    }
}
