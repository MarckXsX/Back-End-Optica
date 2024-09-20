package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Especialidad;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.repositories.EspecialidadRepository;
import com.springboot.sv.optica.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository repository;

    @Override
    public List<Especialidad> findAll() {
        return (List<Especialidad>) repository.findAll();
    }

    @Override
    public Optional<Especialidad> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Especialidad save(Especialidad especialidad) {
        return repository.save(especialidad);
    }

    @Override
    public Optional<Especialidad> update(Long id, Especialidad especialidad) {
        Optional<Especialidad> optionalEspecialidad = repository.findById(id);
        if(optionalEspecialidad.isPresent()){
            Especialidad especialidadDB = optionalEspecialidad.orElseThrow();
            especialidadDB.setNombre(especialidad.getNombre());
            especialidadDB.setDescripcion(especialidad.getDescripcion());

            return Optional.of(repository.save(especialidadDB));
        }
        return optionalEspecialidad;
    }

    @Override
    public Optional<Especialidad> delete(Long id) {
        Optional<Especialidad> optionalEspecialidad = repository.findById(id);
        if(optionalEspecialidad.isPresent()){
            Especialidad especialidadDB = optionalEspecialidad.orElseThrow();
            if(especialidadDB.getDoctors().isEmpty()){
                repository.delete(especialidadDB);
                return  optionalEspecialidad;
            }
            return Optional.empty();
        }
        return optionalEspecialidad;
    }
}
