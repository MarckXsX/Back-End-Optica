package com.springboot.sv.optica.entities.dto;


import com.springboot.sv.optica.validation.IsCitaLibre;
import com.springboot.sv.optica.validation.IsExistsCita;
import com.springboot.sv.optica.validation.IsExistsDoctor;
import com.springboot.sv.optica.validation.IsRequired;

public class ConsultaDTO {

    @IsExistsCita
    //@IsCitaLibre
    private Long cita;

    @IsExistsDoctor
    private Long doctor;

    @IsRequired
    private String diagnostico;

    @IsRequired
    private String instrucciones;

    public Long getCita() {
        return cita;
    }

    public void setCita(Long cita) {
        this.cita = cita;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }
}
