package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Aro;
import com.springboot.sv.optica.entities.FacturaProducto;
import com.springboot.sv.optica.entities.Graduacion;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.FacturaProductoDTO;
import com.springboot.sv.optica.repositories.AroRepository;
import com.springboot.sv.optica.repositories.FacturaProductoRepository;
import com.springboot.sv.optica.repositories.GraduacionRepository;
import com.springboot.sv.optica.repositories.PacienteRepository;
import com.springboot.sv.optica.services.FacturaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaProductoImpl implements FacturaProductoService {

    @Autowired
    private FacturaProductoRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private GraduacionRepository graduacionRepository;

    @Autowired
    private AroRepository aroRepository;

    @Override
    public List<FacturaProducto> findAll() {
        return (List<FacturaProducto>) repository.findAll();
    }

    @Override
    public Optional<FacturaProducto> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<FacturaProducto> save(FacturaProductoDTO facturaDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(facturaDTO.getPaciente());
        Optional<Aro> optionalAro = aroRepository.findById(facturaDTO.getAro());
        Optional<Graduacion> optionalGraduacion = graduacionRepository.findById(facturaDTO.getGraduacion());

        if(optionalGraduacion.isPresent() && optionalPaciente.isPresent() && optionalAro.isPresent()){
            Paciente pacienteDB = optionalPaciente.orElseThrow();
            Aro aroDB = optionalAro.orElseThrow();
            Graduacion graduacionDB = optionalGraduacion.orElseThrow();

            if(graduacionDB.getFacturaProducto() == null){

                Double Monto = aroDB.getPrecio() + graduacionDB.getCosto_lente();

                FacturaProducto facturaProducto = new FacturaProducto();
                facturaProducto.setPaciente(pacienteDB);
                facturaProducto.setAro(aroDB);
                facturaProducto.setGraduacion(graduacionDB);
                facturaProducto.setMonto_total(Monto);
                facturaProducto.setFecha_emision(facturaDTO.getFecha_emision());
                facturaProducto.setEstado(facturaDTO.getEstado());

                return Optional.of(repository.save(facturaProducto));
            }
            return Optional.empty();

        }
        return Optional.empty();
    }

    @Override
    public Optional<FacturaProducto> update(Long id, FacturaProductoDTO facturaDTO) {
        Optional<FacturaProducto> optionalFacturaProducto = repository.findById(id);
        if(optionalFacturaProducto.isPresent()){
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(facturaDTO.getPaciente());
            Optional<Aro> optionalAro = aroRepository.findById(facturaDTO.getAro());
            Optional<Graduacion> optionalGraduacion = graduacionRepository.findById(facturaDTO.getGraduacion());

            if(optionalGraduacion.isPresent() && optionalPaciente.isPresent() && optionalAro.isPresent()){
                Paciente pacienteDB = optionalPaciente.orElseThrow();
                Aro aroDB = optionalAro.orElseThrow();
                Graduacion graduacionDB = optionalGraduacion.orElseThrow();
                FacturaProducto facturaProductoDB = optionalFacturaProducto.orElseThrow();

                Double Monto = aroDB.getPrecio() + graduacionDB.getCosto_lente();

                facturaProductoDB.setPaciente(pacienteDB);
                facturaProductoDB.setAro(aroDB);
                facturaProductoDB.setGraduacion(graduacionDB);
                facturaProductoDB.setMonto_total(Monto);
                facturaProductoDB.setFecha_emision(facturaDTO.getFecha_emision());
                facturaProductoDB.setEstado(facturaDTO.getEstado());

                return Optional.of(repository.save(facturaProductoDB));
            }
            return Optional.empty();
        }
        return optionalFacturaProducto;
    }

    @Override
    public Optional<FacturaProducto> delete(Long id) {
        Optional<FacturaProducto> optionalFacturaProducto = repository.findById(id);
        optionalFacturaProducto.ifPresent(facturaProducto -> {
            repository.delete(facturaProducto);
        });
        return optionalFacturaProducto;
    }
}
