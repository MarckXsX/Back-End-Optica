package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.Doctor;
import com.springboot.sv.optica.entities.dto.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAll();

    Optional<Doctor> findById(Long id);

    Optional<Doctor> save(DoctorDTO doctorDTO);

    Optional<Doctor> update(Long id, DoctorDTO doctorDTO);

    Optional<Doctor> delete(Long id);
}
