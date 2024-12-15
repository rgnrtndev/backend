package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
