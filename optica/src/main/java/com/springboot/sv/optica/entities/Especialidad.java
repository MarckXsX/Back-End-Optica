package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.sv.optica.validation.IsRequired;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequired
    private String nombre;

    @IsRequired
    private String descripcion;

    //@JsonBackReference // Indica que esta parte debe ser ignorada durante la serialización para evitar recursividad
    //@JsonIgnoreProperties({"especialidad","handler","hibernateLazyInitializer"})
    @JsonIgnore
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
