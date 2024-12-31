package com.rcc.dev.backend.service.department.iservice;

import com.rcc.dev.backend.dto.department.DepartmentRequest;
import com.rcc.dev.backend.dto.department.DepartmentRoleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.DepartmentRole;
import jakarta.servlet.http.HttpServletRequest;

public interface DepartmentService {
    RCCResponse<Object> findAllRoleDepartment(HttpServletRequest httpServletRequest);
    RCCResponse<Object> updateRoleDepartment(HttpServletRequest httpServletRequest, DepartmentRoleRequest departmentRoleRequest);
    RCCResponse<Object> detailRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId);
    RCCResponse<Object> deleteRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId);

    RCCResponse<Object> findAllDepartment(HttpServletRequest httpServletRequest);
    RCCResponse<Object> updateDepartment(HttpServletRequest httpServletRequest, DepartmentRequest departmentRequest);
    RCCResponse<Object> detailDepartment(HttpServletRequest httpServletRequest, Long departmentId);
    RCCResponse<Object> deleteDepartment(HttpServletRequest httpServletRequest, Long departmentId);
}
