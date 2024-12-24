package com.formulario.ejercicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private boolean terminos;
    private boolean newsletter;

    

}