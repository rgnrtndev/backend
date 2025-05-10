package com.rcc.dev.backend.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRoleRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 6043474594628806572L;
    private Long id;
    private String roleName;
}
