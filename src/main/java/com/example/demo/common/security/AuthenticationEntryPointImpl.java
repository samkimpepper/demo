package com.example.demo.common.security;

import com.example.demo.common.error.ErrorCode;
import com.example.demo.common.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode exception = (ErrorCode) request.getAttribute("exception");

        if (exception != null) {
            exceptionHandler(response, exception);
        }
    }

    public void exceptionHandler(HttpServletResponse response, ErrorCode error) {
        response.setStatus(error.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ErrorResponse.of(error));
            response.getWriter().write(json);
            log.error(error.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
