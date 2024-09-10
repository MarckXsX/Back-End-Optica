package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.PacienteService;
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
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public List<Paciente> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Paciente> optionalPaciente = service.findById(id);
        if(optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Paciente paciente, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Paciente> optionalPaciente = service.update(id,paciente);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPaciente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Paciente> optionalPaciente = service.delete(id);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.orElseThrow());
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
