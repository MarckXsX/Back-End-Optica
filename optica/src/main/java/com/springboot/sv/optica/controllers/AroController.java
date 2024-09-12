package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Aro;
import com.springboot.sv.optica.services.AroService;
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
@RequestMapping("/api/aros")
public class AroController {

    @Autowired
    private AroService service;

    @GetMapping
    public List<Aro> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Aro> optionalAro = service.findById(id);
        if(optionalAro.isPresent()){
            return ResponseEntity.ok(optionalAro.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Aro aro, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(aro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Aro aro, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Aro> optionalAro = service.update(id,aro);
        if (optionalAro.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalAro.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Aro> optionalAro = service.delete(id);
        if (optionalAro.isPresent()){
            return ResponseEntity.ok(optionalAro.orElseThrow());
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
