package com.rcc.dev.backend.service.role.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.role.RoleRequest;
import com.rcc.dev.backend.model.Role;

import java.util.List;

public interface RoleService {
    RCCResponse<Object> list();
    RCCResponse<Object> detail(Long id);
    RCCResponse<Object> save(RoleRequest roleRequest);
    RCCResponse<Object> delete(Long id);
}
