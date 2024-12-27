package com.formulario.ejercicio.controllers;

import com.formulario.ejercicio.model.Contacto;
import com.formulario.ejercicio.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody Contacto contacto) {
        // Guardamos el usuario en la base de datos
        contactoRepository.save(contacto);

        return ResponseEntity.ok("Formulario recibido y guardado correctamente");
    }

}
