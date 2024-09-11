package com.springboot.sv.optica.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CitaLibreValidation.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsCitaLibre {
    String message() default "La cita ya está asociada a otra consulta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    long consultaId() default -1;

}
