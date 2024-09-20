package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Cita;
import com.springboot.sv.optica.entities.Consulta;
import com.springboot.sv.optica.entities.dto.CitaDTO;
import com.springboot.sv.optica.entities.dto.ConsultaDTO;
import com.springboot.sv.optica.services.CitaService;
import com.springboot.sv.optica.services.ConsultaService;
import com.springboot.sv.optica.validation.CitaLibreValidation;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consulta no encontrada!");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ConsultaDTO consultaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Consulta> optionalConsulta = service.save(consultaDTO);
        if(optionalConsulta.isPresent()){

            return ResponseEntity.ok(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la creacion de la Consulta, Solo se pueden agregar Citas pendientes!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ConsultaDTO consultaDTO, BindingResult result, @PathVariable Long id){

        if(result.hasFieldErrors()){
            return validation(result);
        }

        Optional<Consulta> optionalConsulta = service.update(id,consultaDTO);
        if (optionalConsulta.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalConsulta.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la actualizacion de la Consulta, Cita no permitida, Consulta no encontrada, Consulta no permitida para actualizar!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Consulta> optionalConsulta = service.delete(id);
        if (optionalConsulta.isPresent()){
            return ResponseEntity.ok(optionalConsulta.orElseThrow());
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
