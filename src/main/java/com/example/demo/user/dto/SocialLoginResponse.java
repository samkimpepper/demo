package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLoginResponse {

    private String accessToken;

    private String providerType;

    private String providerUserId;

    public static SocialLoginResponse from(String accessToken) {
        return builder().accessToken(accessToken).build();
    }
}