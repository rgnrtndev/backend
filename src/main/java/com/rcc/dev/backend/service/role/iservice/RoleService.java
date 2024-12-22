package com.rcc.dev.backend.service.role.iservice;

import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.role.RoleRequest;

public interface RoleService {
    RCCResponse<Object> list();
    RCCResponse<Object> detail(Long id);
    RCCResponse<Object> save(RoleRequest roleRequest);
    RCCResponse<Object> delete(Long id);
}
