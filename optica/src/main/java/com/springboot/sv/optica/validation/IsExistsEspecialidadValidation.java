package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Especialidad;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.EspecialidadService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsEspecialidadValidation implements ConstraintValidator<IsExistsEspecialidad, Long> {

    @Autowired
    private EspecialidadService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Especialidad> especialidad = service.findById(value);
        System.out.println("Especialidad = " + especialidad);
        return especialidad.isPresent();
    }
}
