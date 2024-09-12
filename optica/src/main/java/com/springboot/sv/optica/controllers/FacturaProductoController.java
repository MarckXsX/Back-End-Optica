package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.FacturaProducto;
import com.springboot.sv.optica.entities.dto.FacturaProductoDTO;
import com.springboot.sv.optica.services.FacturaProductoService;
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
@RequestMapping("/api/facturasP")
public class FacturaProductoController {

    @Autowired
    private FacturaProductoService service;

    @GetMapping
    public List<FacturaProducto> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<FacturaProducto> optionalFacturaProducto = service.findById(id);
        if(optionalFacturaProducto.isPresent()){
            return ResponseEntity.ok(optionalFacturaProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody FacturaProductoDTO facturaDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<FacturaProducto> optionalFacturaProducto = service.save(facturaDTO);
        if(optionalFacturaProducto.isPresent()){

            return ResponseEntity.ok(optionalFacturaProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody FacturaProductoDTO facturaDTO, BindingResult result, @PathVariable Long id){

        if(result.hasFieldErrors()){
            return validation(result);
        }

        Optional<FacturaProducto> optionalFacturaProducto = service.update(id,facturaDTO);
        if (optionalFacturaProducto.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalFacturaProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<FacturaProducto> optionalFacturaProducto = service.delete(id);
        if (optionalFacturaProducto.isPresent()){
            return ResponseEntity.ok(optionalFacturaProducto.orElseThrow());
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
