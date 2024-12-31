package com.rcc.dev.backend.controller;

import com.rcc.dev.backend.dto.department.DepartmentRequest;
import com.rcc.dev.backend.dto.department.DepartmentRoleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.service.department.iservice.DepartmentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/save/department-role")
    public RCCResponse<Object> updateDepartmentRole(HttpServletRequest httpServletRequest, @RequestBody DepartmentRoleRequest departmentRoleRequest){
        return departmentService.updateRoleDepartment(httpServletRequest, departmentRoleRequest);
    }

    @GetMapping("/list/department-role")
    public RCCResponse<Object> listDepartmentRole(HttpServletRequest httpServletRequest){
        return departmentService.findAllRoleDepartment(httpServletRequest);
    }

    @GetMapping("/detail/department-role/{id}")
    public RCCResponse<Object> detailDepartmentRole(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return departmentService.detailRoleDepartment(httpServletRequest, id);
    }

    @DeleteMapping("/delete/department-role/{id}")
    public RCCResponse<Object> deletedDepartmentRole(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return departmentService.deleteDepartment(httpServletRequest, id);
    }

    @PostMapping("/update/department")
    public RCCResponse<Object> updateDepartment(HttpServletRequest httpServletRequest, @RequestBody DepartmentRequest departmentRequest){
        return departmentService.updateDepartment(httpServletRequest, departmentRequest);
    }

    @GetMapping("/list/department")
    public RCCResponse<Object> listDepartment(HttpServletRequest httpServletRequest){
        return departmentService.findAllDepartment(httpServletRequest);
    }

    @GetMapping("/detail/department/{id}")
    public RCCResponse<Object> detailDepartment(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return departmentService.detailDepartment(httpServletRequest, id);
    }

    @DeleteMapping("/delete/department/{id}")
    public RCCResponse<Object> deleteDepartment(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        return departmentService.deleteDepartment(httpServletRequest, id);
    }
}
