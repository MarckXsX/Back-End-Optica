package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Expediente;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.ExpedienteDTO;
import com.springboot.sv.optica.services.ExpedienteService;
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
@RequestMapping("/api/expedientes")
public class ExpedienteController {

    @Autowired
    private ExpedienteService service;

    @GetMapping
    public List<Expediente> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Expediente> expedienteOptinal = service.findById(id);
        if(expedienteOptinal.isPresent()){
            return ResponseEntity.ok(expedienteOptinal.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expediente no encontrado!");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ExpedienteDTO expedienteDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Expediente> optionalExpediente = service.save(expedienteDTO);
        if(optionalExpediente.isPresent()){

            return ResponseEntity.ok(optionalExpediente.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la creacion del expediente, Paciente ya registrado con otro expediente!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ExpedienteDTO expedienteDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Expediente> optionalExpediente = service.update(id,expedienteDTO);
        if (optionalExpediente.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalExpediente.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la actualizacion del expediente, Paciente no permitido o Expediente no encontrado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Expediente> optionalExpediente = service.delete(id);
        if (optionalExpediente.isPresent()){
            return ResponseEntity.ok(optionalExpediente.orElseThrow());
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
