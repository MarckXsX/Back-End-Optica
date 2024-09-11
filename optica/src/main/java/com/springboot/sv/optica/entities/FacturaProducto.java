package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "facturas_productos")
public class FacturaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"facturaProductoList","handler","hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @JsonIgnoreProperties({"facturaProductoList","handler","hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "id_aro")
    private Aro aro;

    @JsonIgnoreProperties({"facturaProducto","handler","hibernateLazyInitializer"})
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_graduacion")
    private Graduacion graduacion;

    private Double monto_total;

    private String fecha_emision;

    private String estado;

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

    public Aro getAro() {
        return aro;
    }

    public void setAro(Aro aro) {
        this.aro = aro;
    }

    public Graduacion getGraduacion() {
        return graduacion;
    }

    public void setGraduacion(Graduacion graduacion) {
        this.graduacion = graduacion;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
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
