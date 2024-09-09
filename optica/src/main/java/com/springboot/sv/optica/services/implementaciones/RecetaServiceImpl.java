package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.*;
import com.springboot.sv.optica.entities.dto.RecetaDTO;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import com.springboot.sv.optica.repositories.MedicamentoRepository;
import com.springboot.sv.optica.repositories.RecetaRepository;
import com.springboot.sv.optica.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository repository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Receta> findAll() {
        return (List<Receta>) repository.findAll();
    }

    @Override
    public Optional<Receta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Receta> save(RecetaDTO recetaDTO) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(recetaDTO.getConsulta());
        Optional<Medicamento> optionalMedicamento = medicamentoRepository.findById(recetaDTO.getMedicamento());
        if(optionalConsulta.isPresent() && optionalMedicamento.isPresent()){
            Consulta consultaDB = optionalConsulta.orElseThrow();
            Medicamento medicamentoDB = optionalMedicamento.orElseThrow();
            Receta receta = new Receta();
            receta.setConsulta(consultaDB);
            receta.setMedicamento(medicamentoDB);
            receta.setCantidad(recetaDTO.getCantidad());
            return Optional.of(repository.save(receta));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Receta> update(Long id, RecetaDTO recetaDTO) {
        Optional<Receta> optionalReceta = repository.findById(id);
        if(optionalReceta.isPresent()){
            Optional<Consulta> optionalConsulta = consultaRepository.findById(recetaDTO.getConsulta());
            Optional<Medicamento> optionalMedicamento = medicamentoRepository.findById(recetaDTO.getMedicamento());
            if(optionalConsulta.isPresent() && optionalMedicamento.isPresent()){
                Consulta consultaDB = optionalConsulta.orElseThrow();
                Medicamento medicamentoDB = optionalMedicamento.orElseThrow();
                Receta recetaDB = optionalReceta.orElseThrow();
                recetaDB.setConsulta(consultaDB);
                recetaDB.setMedicamento(medicamentoDB);
                recetaDB.setCantidad(recetaDTO.getCantidad());
                return Optional.of(repository.save(recetaDB));
            }
            return Optional.empty();
        }
        return  optionalReceta;
    }

    @Override
    public Optional<Receta> delete(Long id) {
        Optional<Receta> optionalReceta = repository.findById(id);
        optionalReceta.ifPresent(receta -> {
            //consulta.setCita(null);
            repository.delete(receta);
        });
        return optionalReceta;
    }
}
