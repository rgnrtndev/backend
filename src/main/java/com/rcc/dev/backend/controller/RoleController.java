package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.role.RoleRequest;
import com.rcc.dev.backend.model.Role;
import com.rcc.dev.backend.service.role.iservice.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/save")
    public RCCResponse<Object> save(@RequestBody RoleRequest roleRequest){
        return roleService.save(roleRequest);
    }

    @GetMapping("/list")
    public RCCResponse<Object> list(){
        return roleService.list();
    }

    @GetMapping("/detail/{id}")
    public RCCResponse<Object> detail(@PathVariable("id") Long id){
        return roleService.detail(id);
    }
}
