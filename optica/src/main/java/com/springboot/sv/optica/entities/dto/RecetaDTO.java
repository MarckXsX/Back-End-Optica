package com.springboot.sv.optica.entities.dto;

public class RecetaDTO {

    private Long consulta;
    private Long medicamento;
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
