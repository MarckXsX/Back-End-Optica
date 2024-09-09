package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> create(@RequestBody CitaDTO citaDTO){
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
}
