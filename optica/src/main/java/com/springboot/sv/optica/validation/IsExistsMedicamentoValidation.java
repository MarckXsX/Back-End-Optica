package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.services.MedicamentoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsMedicamentoValidation implements ConstraintValidator<IsExistsMedicamento, Long> {

    @Autowired
    private MedicamentoService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        if(value == null){
            return false;
        }
        Optional<Medicamento> medicamento = service.findById(value);
        System.out.println("medicamento = " + medicamento);
        return medicamento.isPresent();
    }
}
