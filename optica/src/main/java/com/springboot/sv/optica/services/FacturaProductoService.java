package com.springboot.sv.optica.services;


import com.springboot.sv.optica.entities.FacturaProducto;
import com.springboot.sv.optica.entities.dto.FacturaProductoDTO;

import java.util.List;
import java.util.Optional;

public interface FacturaProductoService {

    List<FacturaProducto> findAll();

    Optional<FacturaProducto> findById(Long id);

    Optional<FacturaProducto> save(FacturaProductoDTO facturaDTO);

    Optional<FacturaProducto> update(Long id, FacturaProductoDTO facturaDTO);

    Optional<FacturaProducto> delete(Long id);
}
