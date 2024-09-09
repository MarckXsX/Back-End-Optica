package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.Medicamento;
import com.springboot.sv.optica.services.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> create(@RequestBody Medicamento medicamento){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(medicamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Medicamento medicamento){
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
}
