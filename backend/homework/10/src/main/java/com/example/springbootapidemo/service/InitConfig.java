package com.example.springbootapidemo.service;

import com.example.springbootapidemo.constants.Constants;
import com.example.springbootapidemo.model.AuthUser;
import com.example.springbootapidemo.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InitConfig implements CommandLineRunner {

    private AuthRepository authRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitConfig(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        authRepository.addAuthUser(new AuthUser("Asish", "asish918", passwordEncoder.encode(Constants.AUTH_PASSWORD), "ROLE_ADMIN"));
        authRepository.addAuthUser(new AuthUser("Arjun", "arjun918", passwordEncoder.encode(Constants.AUTH_PASSWORD), "ROLE_BASIC"));
        log.info("Auth users added successfully...");
    }
}
