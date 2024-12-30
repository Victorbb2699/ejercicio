package com.formulario.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formulario.ejercicio.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
