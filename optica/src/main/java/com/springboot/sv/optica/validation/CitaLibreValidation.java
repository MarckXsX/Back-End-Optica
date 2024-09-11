package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.repositories.ConsultaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CitaLibreValidation implements ConstraintValidator<IsCitaLibre, Long> {

    @Autowired
    private ConsultaRepository repository;

    private Long consultaId;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if (value == null) {
            return false;
        }

        return !repository.existsByCitaId(value);
    }


}
