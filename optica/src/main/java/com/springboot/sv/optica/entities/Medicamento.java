package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.sv.optica.validation.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "medicamentos")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
    private List<Receta> recetas;

    @JsonIgnore
    @ManyToMany(mappedBy = "medicamentos", cascade = CascadeType.ALL)
    private List<FacturaCita> facturaCitas;

    @IsRequired
    private String nombre;

    @IsRequired
    private String fabricante;

    @IsRequired
    private String via_administracion;

    @Positive(message = "{positive.message}")
    @NotNull(message = "{NotNull.message}")
    private Double costo;

    @Positive(message = "{positive.message}")
    @NotNull(message = "{NotNull.message}")
    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getVia_administracion() {
        return via_administracion;
    }

    public void setVia_administracion(String via_administracion) {
        this.via_administracion = via_administracion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
