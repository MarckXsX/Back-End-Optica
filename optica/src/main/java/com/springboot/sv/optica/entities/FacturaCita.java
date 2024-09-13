package com.springboot.sv.optica.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "facturas_citas")
public class FacturaCita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"facturaCitaList","handler","hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @JsonIgnoreProperties({"facturaCita","handler","hibernateLazyInitializer"})
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_cita")
    private Cita cita;

    @JsonIgnoreProperties({"facturaCitas","handler","hibernateLazyInitializer"})
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "detalle_factura",
            joinColumns = @JoinColumn(name = "id_factura"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_factura","id_medicamento"})
    )
    private List<Medicamento> medicamentos;

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

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
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
