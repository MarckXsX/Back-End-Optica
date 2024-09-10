package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.services.MedicamentoService;
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
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @GetMapping
    public List<Medicamento> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Medicamento> optionalMedicamento = service.findById(id);
        if(optionalMedicamento.isPresent()){
            return ResponseEntity.ok(optionalMedicamento.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody Medicamento medicamento, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(medicamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Medicamento medicamento, BindingResult result, @PathVariable Long id){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Medicamento> optionalMedicamento = service.update(id,medicamento);
        if (optionalMedicamento.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalMedicamento.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Medicamento> optionalMedicamento = service.delete(id);
        if (optionalMedicamento.isPresent()){
            return ResponseEntity.ok(optionalMedicamento.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result){ //DEFINIR ESTE METODO SIEMPRE
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "En el campo " + err.getField() + ", Presenta el siguiente error: " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
