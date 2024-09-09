package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Especialidad;
import com.springboot.sv.optica.entities.Paciente;
import com.springboot.sv.optica.services.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    @GetMapping
    public List<Especialidad> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Especialidad> optionalEspecialidad = service.findById(id);
        if(optionalEspecialidad.isPresent()){
            return ResponseEntity.ok(optionalEspecialidad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Especialidad especialidad){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(especialidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Especialidad especialidad){
        Optional<Especialidad> optionalEspecialidad = service.update(id,especialidad);
        if (optionalEspecialidad.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalEspecialidad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Especialidad> optionalEspecialidad = service.delete(id);
        if (optionalEspecialidad.isPresent()){
            return ResponseEntity.ok(optionalEspecialidad.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
