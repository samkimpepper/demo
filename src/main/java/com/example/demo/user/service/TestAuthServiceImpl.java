package com.example.demo.user.service;

import com.example.demo.common.error.EntityNotFoundException;
import com.example.demo.common.error.ErrorCode;
import com.example.demo.common.security.JwtUtil;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.TokenResponse;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestAuthServiceImpl implements TestAuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public TokenResponse testLogin(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, request.getEmail() + ""));
        return TokenResponse.builder()
                .accessToken(jwtUtil.createToken(user.getId(), user.getCreatedAt()))
                .build();
    }
}
