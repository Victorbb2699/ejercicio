package com.formulario.ejercicio.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formulario.ejercicio.dto.UserDTO;
import com.formulario.ejercicio.mappers.UserMapper;
import com.formulario.ejercicio.model.User;
import com.formulario.ejercicio.repository.UserRepository;
import com.formulario.ejercicio.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    private UserMapper map;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = map.toEntity(userDTO);
        return map.toDto(userRepository.save(user));
    }

    public boolean validateUser(String email, String passwrd) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(passwrd)) {
            return true;
        }
        return false;
    }

}
