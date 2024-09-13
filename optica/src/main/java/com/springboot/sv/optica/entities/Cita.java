package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonManagedReference // Indica que esta parte se debe incluir en la serialización
    @JsonIgnoreProperties({"cita","handler","hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "cita")
    private Consulta consulta;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "cita")
    private FacturaCita facturaCita;

    private String fecha_cita;

    private String hora_cita;

    private String estado;

    private double costo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
