package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.entities.dto.ConsultaDTO;
import com.springboot.sv.optica.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultasController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<Consulta> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Consulta> optionalConsulta = service.findById(id);
        if(optionalConsulta.isPresent()){
            return ResponseEntity.ok(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ConsultaDTO consultaDTO){
        Optional<Consulta> optionalConsulta = service.save(consultaDTO);
        if(optionalConsulta.isPresent()){

            return ResponseEntity.ok(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO){
        Optional<Consulta> optionalConsulta = service.update(id,consultaDTO);
        if (optionalConsulta.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Consulta> optionalConsulta = service.delete(id);
        if (optionalConsulta.isPresent()){
            return ResponseEntity.ok(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
