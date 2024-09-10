package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Doctor;
import com.springboot.sv.optica.entities.Expediente;
import com.springboot.sv.optica.entities.dto.DoctorDTO;
import com.springboot.sv.optica.entities.dto.ExpedienteDTO;
import com.springboot.sv.optica.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping
    public List<Doctor> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Doctor> doctorOptional = service.findById(id);
        if(doctorOptional.isPresent()){
            return ResponseEntity.ok(doctorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorDTO doctorDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Doctor> optionalDoctor = service.save(doctorDTO);
        if(optionalDoctor.isPresent()){

            return ResponseEntity.ok(optionalDoctor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody DoctorDTO doctorDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Doctor> optionalDoctor = service.update(id,doctorDTO);
        if (optionalDoctor.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalDoctor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Doctor> optionalDoctor = service.delete(id);
        if (optionalDoctor.isPresent()){
            return ResponseEntity.ok(optionalDoctor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "En el campo " + err.getField() + ", Presenta el siguiente error: " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
