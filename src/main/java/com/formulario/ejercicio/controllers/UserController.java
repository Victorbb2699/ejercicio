package com.formulario.ejercicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.ejercicio.dto.UserDTO;
import com.formulario.ejercicio.services.servicesImpl.UserServicesImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServicesImpl userService;

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        // Validar si el usuario ha aceptado los términos
        if (!userDTO.isTerminos()) {
            return ResponseEntity.badRequest().body("Debes aceptar los términos.");
        }
        return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.CREATED);
    }

    // Endpoint para validar el login de un usuario
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        // Validar las credenciales
        boolean success = userService.validateUser(userDTO.getEmail(), userDTO.getPassword());
        if (success) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas.");
        }
    }

}
