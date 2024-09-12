package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsConsulta;
import com.springboot.sv.optica.validation.IsFecha;
import com.springboot.sv.optica.validation.IsRequired;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class GraduacionDTO {

    @IsExistsConsulta
    private Long consulta;

    @IsRequired
    private String adicion;

    @IsFecha
    private String fecha_emision;

    @IsRequired
    private String detalle_graduacion;

    @Positive(message = "{positive.message}")
    @NotNull(message = "{NotNull.message}")
    private Double costo_lente;

    public Long getConsulta() {
        return consulta;
    }

    public void setConsulta(Long consulta) {
        this.consulta = consulta;
    }

    public String getAdicion() {
        return adicion;
    }

    public void setAdicion(String adicion) {
        this.adicion = adicion;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getDetalle_graduacion() {
        return detalle_graduacion;
    }

    public void setDetalle_graduacion(String detalle_graduacion) {
        this.detalle_graduacion = detalle_graduacion;
    }

    public Double getCosto_lente() {
        return costo_lente;
    }

    public void setCosto_lente(Double costo_lente) {
        this.costo_lente = costo_lente;
    }
}
