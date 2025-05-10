package com.rcc.dev.backend.service.department.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.department.DepartmentRequest;
import com.rcc.dev.backend.dto.department.DepartmentRoleRequest;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.model.Department;
import com.rcc.dev.backend.model.DepartmentRole;
import com.rcc.dev.backend.repository.DepartmentRepository;
import com.rcc.dev.backend.repository.DepartmentRoleRepository;
import com.rcc.dev.backend.service.department.iservice.DepartmentService;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentRoleRepository departmentRoleRepository;

    @Override
    public ResponseEntity<RCCResponse<Object>> findAllRoleDepartment(HttpServletRequest httpServletRequest) {
        try {
            var roles = departmentRoleRepository.findAll();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    roles
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> updateRoleDepartment(HttpServletRequest httpServletRequest, DepartmentRoleRequest departmentRoleRequest) {
        try {
            DepartmentRole departmentRole;
            if(Objects.isNull(departmentRoleRequest.getId()) || departmentRoleRequest.getId().equals(0L)){
                departmentRole = DepartmentRole.builder()
                        .roleName(departmentRoleRequest.getRoleName())
                        .build();
            }else{
                var department = departmentRoleRepository.findById(departmentRoleRequest.getId());
                if(department.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                            ResponseCode.ERROR_RESPONSE_CODE,
                            ResponseCode.CommonIdn.DATA_NOT_FOUND,
                            ResponseCode.CommonEng.DATA_NOT_FOUND
                    ));
                }
                departmentRole = department.get();
                departmentRole.setRoleName(departmentRoleRequest.getRoleName());
            }
            var saveDepartmentRole = departmentRoleRepository.save(departmentRole);
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    saveDepartmentRole
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> detailRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId) {
        try{
            var department = departmentRoleRepository.findById(roleDepartmentId);
            if(department.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                        ResponseCode.ERROR_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    department.get()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> deleteRoleDepartment(HttpServletRequest httpServletRequest, Long roleDepartmentId) {
        try {
            var department = departmentRoleRepository.findById(roleDepartmentId);
            if(department.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                        ResponseCode.ERROR_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            departmentRoleRepository.delete(department.get());
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Transactional
    @Override
    public ResponseEntity<RCCResponse<Object>> findAllDepartment(HttpServletRequest httpServletRequest) {
        try {
            var departments = departmentRepository.findAll();
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    departments
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> updateDepartment(HttpServletRequest httpServletRequest, DepartmentRequest departmentRequest) {
        try{
            var department = new Department();
            if(Objects.isNull(departmentRequest.getId()) || departmentRequest.getId().equals(0L)){
                department = new Department();
                department.setDepartmentName(departmentRequest.getDepartmentName());
                department.setUserId(departmentRequest.getUserId());
                if(!Objects.isNull(departmentRequest.getRoleId()) || departmentRequest.getRoleId().equals(0L)){
                    var role = departmentRoleRepository.findById(departmentRequest.getRoleId());
                    department.setDepartmentRole(role.get());
                }
            }else{
                var departmentOpt = departmentRepository.findById(departmentRequest.getId());
                if(departmentOpt.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                            ResponseCode.ERROR_RESPONSE_CODE,
                            ResponseCode.CommonIdn.DATA_NOT_FOUND,
                            ResponseCode.CommonEng.DATA_NOT_FOUND
                    ));
                }
                department = departmentOpt.get();
                department.setDepartmentName(department.getDepartmentName());
                department.setUserId(departmentRequest.getUserId());
                if(!Objects.isNull(departmentRequest.getRoleId()) || departmentRequest.getRoleId().equals(0L)){
                    var role = departmentRoleRepository.findById(departmentRequest.getRoleId());
                    department.setDepartmentRole(role.get());
                }
            }

            department = departmentRepository.save(department);

            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    department
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> detailDepartment(HttpServletRequest httpServletRequest, Long departmentId) {
        try {
            var departmentOpt = departmentRepository.findById(departmentId);
            if(departmentOpt.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                        ResponseCode.ERROR_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            return ResponseEntity.ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    departmentOpt.get()
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> deleteDepartment(HttpServletRequest httpServletRequest, Long departmentId) {
        try {
            var departmentOpt = departmentRepository.findById(departmentId);
            if(departmentOpt.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtil.response(
                        ResponseCode.ERROR_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND
                ));
            }
            departmentRepository.delete(departmentOpt.get());
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_DELETED_DATA,
                    ResponseCode.CommonEng.SUCCESS_DELETED_DATA
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            ));
        }
    }
}
