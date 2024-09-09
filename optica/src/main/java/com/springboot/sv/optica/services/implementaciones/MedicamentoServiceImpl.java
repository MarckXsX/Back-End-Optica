package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.repositories.MedicamentoRepository;
import com.springboot.sv.optica.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    @Autowired
    private MedicamentoRepository repository;

    @Override
    public List<Medicamento> findAll() {
        return (List<Medicamento>) repository.findAll();
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    @Override
    public Optional<Medicamento> update(Long id, Medicamento medicamento) {
        Optional<Medicamento> optionalMedicamento = repository.findById(id);
        if(optionalMedicamento.isPresent()){
            Medicamento medicamentoDB = optionalMedicamento.orElseThrow();
            medicamentoDB.setNombre(medicamento.getNombre());
            medicamentoDB.setFabricante(medicamento.getFabricante());
            medicamentoDB.setVia_administracion(medicamento.getVia_administracion());
            medicamentoDB.setCosto(medicamento.getCosto());
            medicamentoDB.setStock(medicamento.getStock());

            return Optional.of(repository.save(medicamentoDB));
        }
        return optionalMedicamento;
    }

    @Override
    public Optional<Medicamento> delete(Long id) {
        Optional<Medicamento> optionalMedicamento = repository.findById(id);
        optionalMedicamento.ifPresent(medicamento -> {
            repository.delete(medicamento);
        });
        return optionalMedicamento;
    }
}
