package com.dictionaryapp.service;

import com.dictionaryapp.model.dto.RegistrationDto;
import com.dictionaryapp.model.entity.UserEntity;

public interface UserService {
    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    void saveUser(RegistrationDto user);
}
