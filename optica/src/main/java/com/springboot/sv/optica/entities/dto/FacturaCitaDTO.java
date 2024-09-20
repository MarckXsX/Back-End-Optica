package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsCita;
import com.springboot.sv.optica.validation.IsExistsPaciente;
import com.springboot.sv.optica.validation.IsFecha;
import com.springboot.sv.optica.validation.IsRequired;

public class FacturaCitaDTO {

    @IsExistsPaciente
    private Long paciente;

    @IsExistsCita
    private Long cita;

    @IsFecha
    private String fehca;

    @IsRequired
    private String estado;

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public Long getCita() {
        return cita;
    }

    public void setCita(Long cita) {
        this.cita = cita;
    }

    public String getFehca() {
        return fehca;
    }

    public void setFehca(String fehca) {
        this.fehca = fehca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
