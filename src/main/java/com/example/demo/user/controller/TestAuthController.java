package com.example.demo.user.controller;

import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.SignupRequest;
import com.example.demo.user.dto.TokenResponse;
import com.example.demo.user.service.TestAuthService;
import com.example.demo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "인증 API")
public class TestAuthController {

    private final TestAuthService authService;

    private final UserService userService;

    @Operation(summary = "테스트 로그인")
    @PostMapping("/testLogin")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<TokenResponse> testLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.testLogin(request));
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signUp")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signUp(request));
    }
}
