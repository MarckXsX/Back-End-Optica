package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<ExistByUserName, String> {

    @Autowired
    private UserService service;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(service == null){
            return true;
        }
        return !service.existsByUsername(username);
    }
}
