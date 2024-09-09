package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsPacienteExpediente;
import com.springboot.sv.optica.validation.IsFecha;
import com.springboot.sv.optica.validation.IsRequired;

import java.util.Date;

public class ExpedienteDTO {

    @IsExistsPacienteExpediente
    //@IsRequired
    private Long paciente;

    @IsRequired
    private String observaciones;

    //@IsRequired
    @IsFecha
    private String fecha_registro;

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
}
