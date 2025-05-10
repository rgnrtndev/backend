package com.rcc.dev.backend.service.department.iservice;

import com.rcc.dev.backend.dto.department.DepartmentRequest;
import com.rcc.dev.backend.dto.department.DepartmentRoleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.DepartmentRole;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<RCCResponse<Object>> findAllRoleDepartment(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> updateRoleDepartment(HttpServletRequest httpServletRequest, DepartmentRoleRequest departmentRoleRequest);
    ResponseEntity<RCCResponse<Object>> detailRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId);
    ResponseEntity<RCCResponse<Object>> deleteRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId);

    ResponseEntity<RCCResponse<Object>> findAllDepartment(HttpServletRequest httpServletRequest);
    ResponseEntity<RCCResponse<Object>> updateDepartment(HttpServletRequest httpServletRequest, DepartmentRequest departmentRequest);
    ResponseEntity<RCCResponse<Object>> detailDepartment(HttpServletRequest httpServletRequest, Long departmentId);
    ResponseEntity<RCCResponse<Object>> deleteDepartment(HttpServletRequest httpServletRequest, Long departmentId);
}
