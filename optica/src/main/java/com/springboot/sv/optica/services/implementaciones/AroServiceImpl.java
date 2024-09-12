package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Aro;
import com.springboot.sv.optica.repositories.AroRepository;
import com.springboot.sv.optica.services.AroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AroServiceImpl implements AroService {

    @Autowired
    private AroRepository repository;

    @Override
    public List<Aro> findAll() {
        return (List<Aro>) repository.findAll();
    }

    @Override
    public Optional<Aro> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Aro save(Aro aro) {
        return repository.save(aro);
    }

    @Override
    public Optional<Aro> update(Long id, Aro aro) {
        Optional<Aro> optionalAro = repository.findById(id);
        if(optionalAro.isPresent()){
            Aro aroDB = optionalAro.orElseThrow();
            aroDB.setModelo(aro.getModelo());
            aroDB.setMarca(aro.getMarca());
            aroDB.setMateria(aro.getMateria());
            aroDB.setPrecio(aro.getPrecio());
            aroDB.setEstado(aro.getEstado());

            return Optional.of(repository.save(aroDB));
        }
        return optionalAro;
    }

    @Override
    public Optional<Aro> delete(Long id) {
        Optional<Aro> optionalAro = repository.findById(id);
        optionalAro.ifPresent(aro -> {
            repository.delete(aro);
        });
        return optionalAro;
    }
}
