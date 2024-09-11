package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "graduaciones")
public class Graduacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"graduacion","handler","hibernateLazyInitializer"})
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true ,mappedBy = "graduacion")
    private FacturaProducto facturaProducto;

    private String adicion;

    private String fecha_emision;

    private String detalle_graduacion;

    private Double costo_lente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
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

    public FacturaProducto getFacturaProducto() {
        return facturaProducto;
    }

    public void setFacturaProducto(FacturaProducto facturaProducto) {
        this.facturaProducto = facturaProducto;
    }
}
