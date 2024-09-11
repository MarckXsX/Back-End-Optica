package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.CitaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsCitaValidation implements ConstraintValidator<IsExistsCita, Long> {

    @Autowired
    private CitaService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Cita> cita = service.findById(value);
        System.out.println("Cita = " + cita);
        return cita.isPresent();
    }
}
