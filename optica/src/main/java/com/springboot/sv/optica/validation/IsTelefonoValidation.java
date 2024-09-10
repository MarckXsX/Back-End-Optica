package com.springboot.sv.optica.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class IsTelefonoValidation implements ConstraintValidator<IsTelefono, String> {

    private static final String TELEFONO_PATTERN = "^\\d{4}-\\d{4}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // O true, si deseas aceptar nulos
        }
        // Verificar si el valor coincide con el patrón
        return value.matches(TELEFONO_PATTERN);
    }
}
