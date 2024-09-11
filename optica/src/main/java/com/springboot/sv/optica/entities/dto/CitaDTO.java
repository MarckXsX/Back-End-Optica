package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsPaciente;
import com.springboot.sv.optica.validation.IsFecha;
import com.springboot.sv.optica.validation.IsRequired;
import com.springboot.sv.optica.validation.IsValidHora;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.lang.NonNull;

import java.util.Date;

public class CitaDTO {

    @IsExistsPaciente
    private Long paciente;

    @IsFecha
    private String fecha_cita;

    @IsValidHora
    private String hora_cita;

    @IsRequired
    private String estado;

    @Positive(message = "{positive.message}")
    @NotNull(message = "{NotNull.message}")
    private double costo;

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
