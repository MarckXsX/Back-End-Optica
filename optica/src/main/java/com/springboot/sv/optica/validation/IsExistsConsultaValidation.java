package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.services.ConsultaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsConsultaValidation implements ConstraintValidator<IsExistsConsulta, Long> {

    @Autowired
    private ConsultaService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Consulta> consulta = service.findById(value);
        System.out.println("consulta = " + consulta);
        return consulta.isPresent();
    }
}
