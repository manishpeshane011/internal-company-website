package com.security.www.service;

import com.security.www.dtos.AuthResponse;
import com.security.www.dtos.LoginRequest;
import com.security.www.dtos.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
