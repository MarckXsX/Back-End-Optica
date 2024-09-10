package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.PacienteService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IsExistsPacienteValidation implements ConstraintValidator<IsExistsPaciente, Long> {

    @Autowired
    private PacienteService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Paciente> expediente = service.findById(value);
        System.out.println("expediente = " + expediente);
        return expediente.isPresent();
    }
}
