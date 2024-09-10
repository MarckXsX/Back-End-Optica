package com.springboot.sv.optica.entities.dto;

import com.springboot.sv.optica.validation.IsExistsEspecialidad;
import com.springboot.sv.optica.validation.IsRequired;
import com.springboot.sv.optica.validation.IsTelefono;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DoctorDTO {

    @IsExistsEspecialidad
    private Long especialidad;

    @IsRequired
    private String nombres;

    @IsRequired
    private String apellidos;

    @IsTelefono
    private String telefono;

    @NotBlank(message = "{NotBlank.correo}")
    @Email(message = "{Email.formato}")
    private String correo;

    @IsRequired //Posible cambio
    private String licencia;

    public Long getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Long especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}
