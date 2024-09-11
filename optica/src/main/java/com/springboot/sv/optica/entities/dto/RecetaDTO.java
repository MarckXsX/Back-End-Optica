package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsConsulta;
import com.springboot.sv.optica.validation.IsExistsMedicamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RecetaDTO {

    @IsExistsConsulta
    private Long consulta;

    @IsExistsMedicamento
    private Long medicamento;

    @Positive(message = "{positive.message}")
    @NotNull(message = "{NotNull.message}")
    private Integer cantidad;

    public Long getConsulta() {
        return consulta;
    }

    public void setConsulta(Long consulta) {
        this.consulta = consulta;
    }

    public Long getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Long medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
