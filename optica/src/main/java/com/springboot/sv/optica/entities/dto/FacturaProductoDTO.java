package com.springboot.sv.optica.entities.dto;


import com.springboot.sv.optica.validation.*;

public class FacturaProductoDTO {

    @IsExistsPaciente
    private Long paciente;

    @IsExistsAro
    private Long aro;

    @IsExistsGraduacion
    private Long graduacion;

    @IsFecha
    private String fecha_emision;

    @IsRequired
    private String estado;

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public Long getAro() {
        return aro;
    }

    public void setAro(Long aro) {
        this.aro = aro;
    }

    public Long getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(Long graduacion) {
        this.graduacion = graduacion;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
