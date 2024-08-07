package com.example.demo.user.controller;

import com.example.demo.common.argumenthandler.Entity;
import com.example.demo.common.security.UserDetailsImpl;
import com.example.demo.user.service.UserService;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.SignupRequest;
import com.example.demo.user.dto.UserDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "회원 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signUp(request));
    }

    @Operation(summary = "회원 상세 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailResponse> getUserDetail(
        @PathVariable long userId,
        @Entity(name = "userId") User user) {
        return ResponseEntity.ok(UserDetailResponse.from(user));
    }

    @GetMapping("/userInfo")
    public ResponseEntity<String> authenticationTest(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userDetails.user().getEmail());
    }
}
