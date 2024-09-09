package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Expediente;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.entities.dto.ExpedienteDTO;
import com.springboot.sv.optica.services.ExpedienteService;
import com.springboot.sv.optica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExpedienteDTO expedienteDTO){
        Optional<Expediente> optionalExpediente = service.save(expedienteDTO);
        if(optionalExpediente.isPresent()){

            return ResponseEntity.ok(optionalExpediente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ExpedienteDTO expedienteDTO){
        Optional<Expediente> optionalExpediente = service.update(id,expedienteDTO);
        if (optionalExpediente.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalExpediente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Expediente> optionalExpediente = service.delete(id);
        if (optionalExpediente.isPresent()){
            return ResponseEntity.ok(optionalExpediente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
