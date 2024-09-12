package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Graduacion;
import com.springboot.sv.optica.entities.dto.GraduacionDTO;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import com.springboot.sv.optica.repositories.GraduacionRepository;
import com.springboot.sv.optica.services.GraduacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraduacionServiceImpl implements GraduacionService {

    @Autowired
    private GraduacionRepository repository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public List<Graduacion> findAll() {
        return (List<Graduacion>) repository.findAll();
    }

    @Override
    public Optional<Graduacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Graduacion> save(GraduacionDTO graduacionDTO) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(graduacionDTO.getConsulta());
        if(optionalConsulta.isPresent()){
            Consulta consultaDB = optionalConsulta.orElseThrow();
            Graduacion graduacion = new Graduacion();
            graduacion.setConsulta(consultaDB);
            graduacion.setAdicion(graduacionDTO.getAdicion());
            graduacion.setFecha_emision(graduacionDTO.getFecha_emision());
            graduacion.setDetalle_graduacion(graduacionDTO.getDetalle_graduacion());
            graduacion.setCosto_lente(graduacionDTO.getCosto_lente());

            return Optional.of(repository.save(graduacion));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Graduacion> update(Long id, GraduacionDTO graduacionDTO) {
        Optional<Graduacion> optionalGraduacion = repository.findById(id);
        if(optionalGraduacion.isPresent()){
            Optional<Consulta> optionalConsulta = consultaRepository.findById(graduacionDTO.getConsulta());
            if(optionalConsulta.isPresent()){
                Graduacion graduacionDB = optionalGraduacion.orElseThrow();
                Consulta consultaDB = optionalConsulta.orElseThrow();
                graduacionDB.setConsulta(consultaDB);
                graduacionDB.setAdicion(graduacionDTO.getAdicion());
                graduacionDB.setFecha_emision(graduacionDTO.getFecha_emision());
                graduacionDB.setDetalle_graduacion(graduacionDTO.getDetalle_graduacion());
                graduacionDB.setCosto_lente(graduacionDTO.getCosto_lente());

                return Optional.of(repository.save(graduacionDB));

            }
            return Optional.empty();
        }
        return optionalGraduacion;
    }

    @Override
    public Optional<Graduacion> delete(Long id) {
        Optional<Graduacion> optionalGraduacion = repository.findById(id);
        optionalGraduacion.ifPresent(graduacion -> {
            repository.delete(graduacion);
        });
        return optionalGraduacion;
    }
}
