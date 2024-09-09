package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.services.CitaService;
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
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Cita> optionalCita = service.findById(id);
        if(optionalCita.isPresent()){
            return ResponseEntity.ok(optionalCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CitaDTO citaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Cita> optionalCita = service.save(citaDTO);
        if(optionalCita.isPresent()){

            return ResponseEntity.ok(optionalCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CitaDTO citaDTO){
        Optional<Cita> optionalCita = service.update(id,citaDTO);
        if (optionalCita.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Cita> optionalCita = service.delete(id);
        if (optionalCita.isPresent()){
            return ResponseEntity.ok(optionalCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
