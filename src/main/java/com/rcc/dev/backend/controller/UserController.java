package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.user.UserRequestDto;
import com.rcc.dev.backend.service.user.iservice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<RCCResponse<Object>> list(HttpServletRequest httpServletRequest){
        return userService.findAll(httpServletRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, @RequestBody UserRequestDto userRequestDto){
        return userService.update(httpServletRequest, userRequestDto);
    }

    @PostMapping("/detail/{id}")
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return userService.detail(httpServletRequest, id);
    }
}
