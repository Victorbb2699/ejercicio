package com.formulario.ejercicio.repository;

import com.formulario.ejercicio.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {

}
