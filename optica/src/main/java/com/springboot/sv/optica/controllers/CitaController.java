package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.services.CitaService;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cita no encontrada!");

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CitaDTO citaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Cita> optionalCita = service.save(citaDTO);
        if(optionalCita.isPresent()){

            return ResponseEntity.ok(optionalCita.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la creacion de la Cita, Valor del campo estado no permitido!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CitaDTO citaDTO, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Cita> optionalCita = service.update(id,citaDTO);
        if (optionalCita.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalCita.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la actualizacion de la Cita, Cita no encontrado, Cita actual no puede modificarse!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Cita> optionalCita = service.delete(id);
        if (optionalCita.isPresent()){
            return ResponseEntity.ok(optionalCita.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la eliminacion de la Cita, Cita no encontrado o Cita asociada a otros registros!");
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
