package com.rcc.dev.backend.service.user.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.user.UserRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    RCCResponse<Object> findAll(HttpServletRequest httpServletRequest);
    RCCResponse<Object> detail(HttpServletRequest httpServletRequest, Long id);
    RCCResponse<Object> update(HttpServletRequest httpServletRequest, UserRequestDto userRequestDto);
}
