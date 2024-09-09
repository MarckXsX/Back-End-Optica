package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> create(@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Paciente paciente){
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

}
