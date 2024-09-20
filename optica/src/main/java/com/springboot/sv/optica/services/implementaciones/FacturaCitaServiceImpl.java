package com.springboot.sv.optica.services.implementaciones;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.FacturaCita;
import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.FacturaCitaDTO;
import com.springboot.sv.optica.repositories.CitaRepository;
import com.springboot.sv.optica.repositories.FacturaCitasRepository;
import com.springboot.sv.optica.repositories.MedicamentoRepository;
import com.springboot.sv.optica.repositories.PacienteRepository;
import com.springboot.sv.optica.services.FacturaCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaCitaServiceImpl implements FacturaCitaService {

    @Autowired
    private FacturaCitasRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<FacturaCita> findAll() {
        return (List<FacturaCita>) repository.findAll();
    }

    @Override
    public Optional<FacturaCita> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<FacturaCita> save(FacturaCitaDTO facturaDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(facturaDTO.getPaciente());
        Optional<Cita> optionalCita = citaRepository.findById(facturaDTO.getCita());
        if(optionalPaciente.isPresent() && optionalCita.isPresent()){
            Paciente pacienteDB = optionalPaciente.orElseThrow();
            Cita citaDB = optionalCita.orElseThrow();
            if(citaDB.getEstado().equalsIgnoreCase("Finalizada") && citaDB.getFacturaCita() == null){
                List<Object[]> data = citaRepository.obtenerMedicamentosCita(facturaDTO.getCita());

                List<Long> listIds = new ArrayList<>(); //almacena los datos de los id de los medicamentos de las citas

                Double total = 0.0;
                Double costoCita = citaDB.getCosto();

                for (Object[] dat: data ){
                    //costoCita = (Double) dat[0]; // Se asigna el costo de la cita
                    total += (Double) dat[2] * (Integer) dat[3]; //Obtengo el valor total de los medicamentos
                    listIds.add((Long) dat[1]); //guardo los id
                }

                total += costoCita;
                Iterable<Medicamento> medicamentos  = medicamentoRepository.findAllById(listIds);
                FacturaCita factura = new FacturaCita();
                factura.setPaciente(pacienteDB);
                factura.setCita(citaDB);
                factura.setMonto_total(total);
                factura.setFecha_emision(facturaDTO.getFehca());
                factura.setEstado(facturaDTO.getEstado());
                factura.setMedicamentos((List<Medicamento>) medicamentos);

                return Optional.of (repository.save(factura));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<FacturaCita> update(Long id, FacturaCitaDTO facturaDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<FacturaCita> delete(Long id) {
        return Optional.empty();
    }
}
