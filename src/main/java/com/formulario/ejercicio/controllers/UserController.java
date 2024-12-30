package com.formulario.ejercicio.controllers;

import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import com.formulario.ejercicio.model.User;
import com.formulario.ejercicio.repository.UserRepository;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final Logger failedLoginLogger = LoggerFactory.getLogger("com.formulario.ejercicio.login");

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        HttpServletRequest request = getCurrentHttpRequest();

        if (request != null) {
            String clientIp = getClientIp(request);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            String os = userAgent.getOperatingSystem().getName();
            String browser = userAgent.getBrowser().getName();

            logger.trace("Información del cliente - IP: {}, Sistema Operativo: {}, Navegador: {}", clientIp, os,
                    browser);
        } else {
            logger.warn("No se pudo obtener la información del cliente en esta solicitud.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            failedLoginLogger.warn("Intento de registro fallido: el correo {} ya está registrado.", user.getEmail());
            return ResponseEntity.status(400).body("El usuario con este correo electrónico ya está registrado.");
        }

        try {
            userRepository.save(user);
            logger.info("Nuevo usuario registrado con correo: {}", user.getEmail());

            return ResponseEntity.ok("Formulario recibido y guardado correctamente");
        } catch (Exception e) {
            logger.error("Error al registrar el usuario con correo: {}. Error: {}", user.getEmail(), e.getMessage());
            return ResponseEntity.status(500).body("Hubo un problema al registrar el usuario.");
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

    private String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}
