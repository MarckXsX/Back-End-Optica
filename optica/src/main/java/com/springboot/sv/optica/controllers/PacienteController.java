package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.*;
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado!");
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<?> viewCita(@PathVariable Long id){
        List<Cita> optionalCita = service.citasPaciente(id);
        if(!optionalCita.isEmpty()){
            return ResponseEntity.ok(optionalCita);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado!");
    }

    @GetMapping("/consultas/{id}")
    public ResponseEntity<?> viewConsulta(@PathVariable Long id){
        List<Consulta> optionalConsulta = service.consultasPaciente(id);
        if(!optionalConsulta.isEmpty()){
            return ResponseEntity.ok(optionalConsulta);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado!");
    }

    @GetMapping("/facturasCitas/{id}")
    public ResponseEntity<?> viewFacturaCita(@PathVariable Long id){
        List<FacturaCita> optionalFacturaCita = service.facturasCitasPaciente(id);
        if(!optionalFacturaCita.isEmpty()){
            return ResponseEntity.ok(optionalFacturaCita);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado!");
    }

    @GetMapping("/facturasLentes/{id}")
    public ResponseEntity<?> viewFacturaLente(@PathVariable Long id){
        List<FacturaProducto> optionalFacturaProducto = service.facturasLentesPaciente(id);
        if(!optionalFacturaProducto.isEmpty()){
            return ResponseEntity.ok(optionalFacturaProducto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente no encontrado!");
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Paciente paciente, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Paciente> optionalPaciente = service.update(id,paciente);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPaciente.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la actualizacion del Paciente, Paciente no encontrado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Paciente> optionalPaciente = service.delete(id);
        if (optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la eliminacion del Paciente, Paciente no encontrado o Paciente asociado a otros registros!");
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "En el campo " + err.getField() + ", Presenta el siguiente error: " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
