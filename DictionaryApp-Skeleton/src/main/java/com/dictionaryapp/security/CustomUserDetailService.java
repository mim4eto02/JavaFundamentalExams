package com.dictionaryapp.security;

import com.dictionaryapp.model.entity.UserEntity;
import com.dictionaryapp.repo.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity != null) {
            User authUser = new User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    Collections.emptyList()
            );
            return authUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
}
