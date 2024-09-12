package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Aro;
import com.springboot.sv.optica.services.AroService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsAroValidation implements ConstraintValidator<IsExistsAro, Long> {

    @Autowired
    private AroService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Aro> aro = service.findById(value);
        System.out.println("aro = " + aro);
        return aro.isPresent();
    }
}
