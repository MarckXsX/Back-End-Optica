package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
