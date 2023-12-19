package com.bonappetit.service;

import com.bonappetit.model.dtos.RegistrationDto;
import com.bonappetit.model.entity.UserEntity;

public interface UserService {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    void saveUser(RegistrationDto user);

    void initAdmin();
}
