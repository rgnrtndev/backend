package com.rcc.dev.backend.service.role.impl;

import com.rcc.dev.backend.dto.role.RoleRequest;
import com.rcc.dev.backend.model.Role;
import com.rcc.dev.backend.repository.RoleRepository;
import com.rcc.dev.backend.service.role.iservice.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @Override
    public Role detail(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role save(RoleRequest roleRequest) {
        Role role = Role.builder()
                .id(roleRequest.getId())
                .roleName(roleRequest.getRoleName()).build();
        return roleRepository.save(role);
    }

    @Override
    public Role delete(Long id) {
        return null;
    }
}
