package com.bonappetit.service.impl;

import com.bonappetit.model.dtos.RegistrationDto;
import com.bonappetit.model.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements com.bonappetit.service.UserService {

    private final com.bonappetit.repo.UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(com.bonappetit.repo.UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(RegistrationDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void initAdmin() {
        if (userRepository.count() == 0) {
            UserEntity userEntity = UserEntity.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("admin@example.com")
                    .build();
            userRepository.save(userEntity);
        }
    }
}
