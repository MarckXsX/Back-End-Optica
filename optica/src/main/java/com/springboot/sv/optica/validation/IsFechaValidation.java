package com.springboot.sv.optica.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Component
public class IsFechaValidation implements ConstraintValidator<IsFecha, String> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // O true, dependiendo de si quieres aceptar nulos
        }
        try {
            // Intenta parsear la fecha usando el formateador
            LocalDate.parse(value, DATE_FORMATTER);
            return true; // Es válido si no lanza una excepción
        } catch (DateTimeParseException e) {
            return false; // No es válido si lanza una excepción
        }
    }
}
