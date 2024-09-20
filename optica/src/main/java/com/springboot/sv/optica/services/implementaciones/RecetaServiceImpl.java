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

        boolean consultaEnFactura = consultaRepository.ExisteConsultaFactura(recetaDTO.getConsulta());
        if(optionalConsulta.isPresent() && optionalMedicamento.isPresent() && !consultaEnFactura){
            Consulta consultaDB = optionalConsulta.orElseThrow();
            Medicamento medicamentoDB = optionalMedicamento.orElseThrow();

            Integer cantidadSoli = recetaDTO.getCantidad();
            Integer stockDisponible = medicamentoDB.getStock();

            if(stockDisponible> cantidadSoli && stockDisponible > 0){
                medicamentoDB.setStock(stockDisponible - cantidadSoli);
                medicamentoRepository.save(medicamentoDB);

                Receta receta = new Receta();
                receta.setConsulta(consultaDB);
                receta.setMedicamento(medicamentoDB);
                receta.setCantidad(recetaDTO.getCantidad());
                return Optional.of(repository.save(receta));
            }else {
                return Optional.empty();
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<Receta> update(Long id, RecetaDTO recetaDTO) {
        Optional<Receta> optionalReceta = repository.findById(id);

        boolean RecetaEnFactura = repository.ExisteRecetaFactura(id);
        if(optionalReceta.isPresent() && !RecetaEnFactura){
            Optional<Consulta> optionalConsulta = consultaRepository.findById(recetaDTO.getConsulta());
            Optional<Medicamento> optionalMedicamento = medicamentoRepository.findById(recetaDTO.getMedicamento());

            boolean medicamentoReceta = repository.existsByMedicamentoIdAndId(recetaDTO.getMedicamento(), id);
            boolean consultaEnFactura = consultaRepository.ExisteConsultaFactura(recetaDTO.getConsulta());

            if(optionalConsulta.isPresent() && optionalMedicamento.isPresent() && !consultaEnFactura){

                Consulta consultaDB = optionalConsulta.orElseThrow();
                Medicamento medicamentoDB = optionalMedicamento.orElseThrow();
                Receta recetaDB = optionalReceta.orElseThrow();

                if(medicamentoReceta){
                    Integer cantidadActual = recetaDB.getCantidad();
                    Integer nuevaCantidad = recetaDTO.getCantidad();
                    Integer stockDisponible = medicamentoDB.getStock();

                    Integer diferencia = nuevaCantidad - cantidadActual;
                    if(diferencia < 0){
                        medicamentoDB.setStock(stockDisponible + Math.abs(diferencia));
                        medicamentoRepository.save(medicamentoDB);
                    } else if (diferencia > 0) {
                        if(stockDisponible> diferencia){
                            medicamentoDB.setStock(stockDisponible - diferencia);
                            medicamentoRepository.save(medicamentoDB);
                        }else {
                            return Optional.empty();
                        }
                    }

                    recetaDB.setConsulta(consultaDB);
                    recetaDB.setMedicamento(medicamentoDB);
                    recetaDB.setCantidad(recetaDTO.getCantidad());
                    return Optional.of(repository.save(recetaDB));

                }else {

                    Medicamento medicamentoAnt = recetaDB.getMedicamento();
                    Integer cantidadAnt = recetaDB.getCantidad();
                    medicamentoAnt.setStock(medicamentoAnt.getStock() + cantidadAnt);
                    medicamentoRepository.save(medicamentoAnt);

                    Integer cantidadSol = recetaDTO.getCantidad();
                    Integer stockDisponible = medicamentoDB.getStock();

                    if(stockDisponible> cantidadSol && stockDisponible > 0){
                        medicamentoDB.setStock(stockDisponible - cantidadSol);
                        medicamentoRepository.save(medicamentoDB);

                        recetaDB.setConsulta(consultaDB);
                        recetaDB.setMedicamento(medicamentoDB);
                        recetaDB.setCantidad(recetaDTO.getCantidad());
                        return Optional.of(repository.save(recetaDB));
                    }else {
                        return Optional.empty();
                    }
                }
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Receta> delete(Long id) {
        Optional<Receta> optionalReceta = repository.findById(id);
        boolean RecetaEnFactura = repository.ExisteRecetaFactura(id);
        if(optionalReceta.isPresent()){
            if(!RecetaEnFactura) {
                Receta receta = optionalReceta.orElseThrow();
                Medicamento medicamento = receta.getMedicamento();
                Integer stock = receta.getCantidad();
                medicamento.setStock(medicamento.getStock() + stock);
                medicamentoRepository.save(medicamento);

                repository.delete(receta);
                return optionalReceta;
            }
            return Optional.empty();
        }
        return optionalReceta;
    }
}
