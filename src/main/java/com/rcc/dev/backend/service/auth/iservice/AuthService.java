package com.rcc.dev.backend.service.auth.iservice;

import com.rcc.dev.backend.dto.auth.LoginRequest;
import com.rcc.dev.backend.dto.auth.RefreshTokenRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    RCCResponse<Object> login(HttpServletRequest httpServletRequest, LoginRequest loginRequest);
    RCCResponse<Object> logout(HttpServletRequest httpServletRequest);
    RCCResponse<Object> refreshToken(HttpServletRequest httpServletRequest, RefreshTokenRequest refreshTokenRequest);
}
