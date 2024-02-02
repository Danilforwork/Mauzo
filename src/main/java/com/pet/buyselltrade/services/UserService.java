package com.pet.buyselltrade.services;

import com.pet.buyselltrade.enums.Role;
import com.pet.buyselltrade.models.UserModel;
import com.pet.buyselltrade.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(UserModel userModel) {
        String email = userModel.getEmail();
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        if (userRepository.findByEmail(email) != null) {
            return false;
        }
        userModel.setActive(true);
        if (userModel.getPassword() != "") {
            System.out.println(userModel.getPassword());
            userModel.setPass(encodedPassword);
            System.out.println(encodedPassword);

        }
        userModel.getRoles().add(Role.ROLE_ADMIN);
        log.info("Saving new User with email: {}", email);

        userRepository.save(userModel);
        return true;
    }

    public List<UserModel> list(){
        return userRepository.findAll();
    }

    public  void banUser(Long id){
        UserModel userModel= userRepository.findById(id).orElse(null);
        if (userModel != null){
            if (userModel.isActive()) {
                userModel.setActive(false);
                log.info("Ban user with id = {}; email: {}", userModel.getId(), userModel.getEmail());
            }
            else {
                userModel.setActive(true);
                log.info("Ban user with id {} ; email : {}", userModel.getId(), userModel.getEmail());
            }
        }
        userRepository.save(userModel);
    }

}
