package com.security.www.service.impl;

import com.security.www.dtos.AuthResponse;
import com.security.www.dtos.LoginRequest;
import com.security.www.dtos.RegisterRequest;
import com.security.www.entity.LoginLogEntity;
import com.security.www.repository.LoginLogRepository;
import com.security.www.service.AuthService;
import com.security.www.util.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
//@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginLogRepository logRepository;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // user creation logic (DB / Feign / IAM)
        String token = jwtService.generateToken(
                request.getUsername(), "EMPLOYEE");

        log.info("User registered: {}", request.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(request.getUsername())
                .role("EMPLOYEE")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {



        logRepository.save(
                LoginLogEntity.builder()
                        .loginMethod("password")
                        .build()
        );

        String token = jwtService.generateToken(
                request.getUsername(), "EMPLOYEE");

        log.info("User logged in: {}", request.getUsername());

        return AuthResponse.builder()
                .token(token)
                .username(request.getUsername())
                .role("EMPLOYEE")
                .build();
    }
}
