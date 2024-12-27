package com.formulario.ejercicio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.formulario.ejercicio.services.servicesImpl.UserServicesImpl;

import lombok.RequiredArgsConstructor;

import com.formulario.ejercicio.model.User;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServicesImpl userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        if (user != null) {
            userService.saveUser(user);
            return new String("USUARIO REGISTRADO CON EXITO");
        } else {
            return new String("ERROR EN EL REGISTRO ");
        }

    }
}
