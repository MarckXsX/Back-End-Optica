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

        boolean consultaEnFactura = consultaRepository.ExisteConsultaFactura(graduacionDTO.getConsulta());
        boolean consultaSinGraduacion = repository.existsByConsultaId(graduacionDTO.getConsulta());

        Consulta consultaDB = optionalConsulta.orElseThrow();
        if(!consultaEnFactura && !consultaSinGraduacion /*|| consultaDB.getGraduacion() == null*/ ){
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

            boolean consultaEnFactura = consultaRepository.ExisteConsultaFactura(graduacionDTO.getConsulta());
            boolean consultaGraduacion = repository.existsByConsultaIdAndId(graduacionDTO.getConsulta(), id);
            boolean consultaSinGraduacion = repository.existsByConsultaId(graduacionDTO.getConsulta());

            Consulta consultaDB = optionalConsulta.orElseThrow();
            Graduacion graduacionDB = optionalGraduacion.orElseThrow();
            if(graduacionDB.getFacturaProducto() == null){ //La graduacion no tiene que estar asociada a una factura
                if(!consultaEnFactura && (consultaGraduacion || !consultaSinGraduacion /*|| consultaDB.getGraduacion() == null*/)){ //Si la consulta que se quiere agregar no esta en algun registro de factura, se podra actualizar
                    graduacionDB.setConsulta(consultaDB);
                    graduacionDB.setAdicion(graduacionDTO.getAdicion());
                    graduacionDB.setFecha_emision(graduacionDTO.getFecha_emision());
                    graduacionDB.setDetalle_graduacion(graduacionDTO.getDetalle_graduacion());
                    graduacionDB.setCosto_lente(graduacionDTO.getCosto_lente());

                    return Optional.of(repository.save(graduacionDB));
                }
                return Optional.empty();

            }
            return Optional.empty();
        }
        return optionalGraduacion;
    }

    @Override
    public Optional<Graduacion> delete(Long id) {
        Optional<Graduacion> optionalGraduacion = repository.findById(id);
        if(optionalGraduacion.isPresent()){
            Graduacion graduacionDB = optionalGraduacion.orElseThrow();
            if(graduacionDB.getFacturaProducto() == null){
                repository.delete(graduacionDB);
                return optionalGraduacion;
            }
        }
        return optionalGraduacion;
    }
}
