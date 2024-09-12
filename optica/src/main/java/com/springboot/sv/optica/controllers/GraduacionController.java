package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Graduacion;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.entities.dto.GraduacionDTO;
import com.springboot.sv.optica.services.GraduacionService;
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
@RequestMapping("/api/graduaciones")
public class GraduacionController {

    @Autowired
    private GraduacionService service;

    @GetMapping
    public List<Graduacion> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Graduacion> optionalGraduacion = service.findById(id);
        if(optionalGraduacion.isPresent()){
            return ResponseEntity.ok(optionalGraduacion.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GraduacionDTO graduacionDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Graduacion> optionalGraduacion = service.save(graduacionDTO);
        if(optionalGraduacion.isPresent()){

            return ResponseEntity.ok(optionalGraduacion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody GraduacionDTO graduacionDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Graduacion> optionalGraduacion = service.update(id,graduacionDTO);
        if (optionalGraduacion.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalGraduacion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Graduacion> optionalGraduacion = service.delete(id);
        if (optionalGraduacion.isPresent()){
            return ResponseEntity.ok(optionalGraduacion.orElseThrow());
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
