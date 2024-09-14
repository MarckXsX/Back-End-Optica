package com.springboot.sv.optica.repositories;

import com.springboot.sv.optica.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
