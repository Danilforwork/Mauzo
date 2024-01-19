package com.pet.buyselltrade.services;

import com.pet.buyselltrade.enums.Role;
import com.pet.buyselltrade.models.UserModel;
import com.pet.buyselltrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserModel userModel,String password) {
        String email = userModel.getEmail();
        String encodedPassword = passwordEncoder.encode(password);
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        userModel.setActive(true);
        userModel.setPass(encodedPassword);
        userModel.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(userModel);
        return true;
    }

}
