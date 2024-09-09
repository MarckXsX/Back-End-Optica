package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Receta;
import com.springboot.sv.optica.entities.dto.RecetaDTO;
import com.springboot.sv.optica.services.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService service;

    @GetMapping
    public List<Receta> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Receta> optionalReceta = service.findById(id);
        if(optionalReceta.isPresent()){
            return ResponseEntity.ok(optionalReceta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RecetaDTO recetaDTO){
        Optional<Receta> optionalReceta = service.save(recetaDTO);
        if(optionalReceta.isPresent()){

            return ResponseEntity.ok(optionalReceta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RecetaDTO recetaDTO){
        Optional<Receta> optionalReceta = service.update(id,recetaDTO);
        if (optionalReceta.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalReceta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Receta> optionalReceta = service.delete(id);
        if (optionalReceta.isPresent()){
            return ResponseEntity.ok(optionalReceta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
