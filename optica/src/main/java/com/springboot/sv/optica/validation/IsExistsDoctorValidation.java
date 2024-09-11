package com.springboot.sv.optica.validation;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Doctor;
import com.springboot.sv.optica.services.DoctorService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IsExistsDoctorValidation implements ConstraintValidator<IsExistsDoctor, Long> {

    @Autowired
    private DoctorService service;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
       if(service == null){
           return true;
       }
        if(value == null){
            return false;
        }
        Optional<Doctor> doctor = service.findById(value);
        System.out.println("Doctor = " + doctor);
        return doctor.isPresent();
    }
}
