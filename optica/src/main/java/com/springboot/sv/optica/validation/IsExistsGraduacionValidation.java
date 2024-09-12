package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Aro;
import com.springboot.sv.optica.entities.Graduacion;
import com.springboot.sv.optica.services.GraduacionService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsGraduacionValidation implements ConstraintValidator<IsExistsGraduacion, Long> {

    @Autowired
    private GraduacionService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Graduacion> graduacion = service.findById(value);
        System.out.println("graduacion = " + graduacion);
        return graduacion.isPresent();
    }
}
