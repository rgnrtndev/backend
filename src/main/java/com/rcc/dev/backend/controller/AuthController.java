package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.auth.LoginRequest;
import com.rcc.dev.backend.dto.auth.RefreshTokenRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.auth.iservice.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public RCCResponse<Object> login(HttpServletRequest httpServletRequest, @RequestBody LoginRequest loginRequest){
        return authService.login(httpServletRequest, loginRequest);
    }

    @PostMapping("/refresh")
    public RCCResponse<Object> refreshToken(HttpServletRequest httpServletRequest, @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(httpServletRequest, refreshTokenRequest);
    }

    @PostMapping("/logout")
    public RCCResponse<Object> logout(HttpServletRequest httpServletRequest){
        return authService.logout(httpServletRequest);
    }
}
