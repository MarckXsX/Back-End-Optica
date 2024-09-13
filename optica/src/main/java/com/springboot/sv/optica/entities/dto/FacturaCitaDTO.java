package com.springboot.sv.optica.entities.dto;

public class FacturaCitaDTO {

    private Long paciente;
    private Long cita;
    private String fehca;
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
