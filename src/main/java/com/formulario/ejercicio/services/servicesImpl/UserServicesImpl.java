package com.formulario.ejercicio.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formulario.ejercicio.model.User;
import com.formulario.ejercicio.repository.UserRepository;
import com.formulario.ejercicio.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean validateUser(String email, String passwrd) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(passwrd)) {
            return true;
        }
        return false;
    }

}
