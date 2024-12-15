package com.rcc.dev.backend.service.role.iservice;

import com.rcc.dev.backend.dto.role.RoleRequest;
import com.rcc.dev.backend.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();
    Role detail(Long id);
    Role save(RoleRequest roleRequest);
    Role delete(Long id);
}
