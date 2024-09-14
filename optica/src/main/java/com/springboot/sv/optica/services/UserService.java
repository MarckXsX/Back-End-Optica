package com.springboot.sv.optica.services;

import com.springboot.sv.optica.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
