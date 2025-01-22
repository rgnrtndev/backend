package com.rcc.dev.backend.service.user.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.user.UserRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<RCCResponse<Object>> findAll(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id);
    ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, UserRequestDto userRequestDto);
}
