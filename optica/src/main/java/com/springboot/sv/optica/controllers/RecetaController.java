package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Receta;
import com.springboot.sv.optica.entities.dto.RecetaDTO;
import com.springboot.sv.optica.services.RecetaService;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Receta no encontrada!");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RecetaDTO recetaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Receta> optionalReceta = service.save(recetaDTO);
        if(optionalReceta.isPresent()){

            return ResponseEntity.ok(optionalReceta.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la creacion de la Receta, Consulta no Permitida o Stock insuficiente!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody RecetaDTO recetaDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Receta> optionalReceta = service.update(id,recetaDTO);
        if (optionalReceta.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalReceta.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la actualizacion de la Receta!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Receta> optionalReceta = service.delete(id);
        if (optionalReceta.isPresent()){
            return ResponseEntity.ok(optionalReceta.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la eliminacion de la Receta!");
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "En el campo " + err.getField() + ", Presenta el siguiente error: " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
