package com.example.demo.user.service;

import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.TokenResponse;

public interface TestAuthService {
    TokenResponse testLogin(LoginRequest request);
}
