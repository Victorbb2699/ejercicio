package com.formulario.ejercicio.mappers;

import org.springframework.stereotype.Component;

import com.formulario.ejercicio.dto.UserDTO;
import com.formulario.ejercicio.model.User;

@Component
public class UserMapper {

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setTerminos(dto.isTerminos());
        user.setNewsletter(dto.isNewsletter());
        return user;
    }

    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setNombre(user.getNombre());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setTerminos(user.isTerminos());
        dto.setNewsletter(user.isTerminos());
        return dto;
    }

}
