package com.springboot.sv.optica.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsExistsGraduacionValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsGraduacion {

    String message() default "No existe registro del Aro!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
