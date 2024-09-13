package com.springboot.sv.optica.controllers;

import com.springboot.sv.optica.entities.FacturaCita;
import com.springboot.sv.optica.entities.FacturaProducto;
import com.springboot.sv.optica.entities.dto.FacturaCitaDTO;
import com.springboot.sv.optica.entities.dto.FacturaProductoDTO;
import com.springboot.sv.optica.services.FacturaCitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facturaC")
public class FacturaCitaController {

    @Autowired
    private FacturaCitaService service;

    @GetMapping
    public List<FacturaCita> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<FacturaCita> optionalFacturaCita = service.findById(id);
        if(optionalFacturaCita.isPresent()){
            return ResponseEntity.ok(optionalFacturaCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FacturaCitaDTO facturaDTO){
        Optional<FacturaCita> optionalFacturaCita = service.save(facturaDTO);
        if(optionalFacturaCita.isPresent()){

            return ResponseEntity.ok(optionalFacturaCita.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
