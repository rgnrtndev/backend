package com.rcc.dev.backend.service.role.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.role.RoleRequest;
import com.rcc.dev.backend.model.Role;
import com.rcc.dev.backend.repository.RoleRepository;
import com.rcc.dev.backend.service.role.iservice.RoleService;
import com.rcc.dev.backend.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RCCResponse<Object> list() {
        try {
            var roles = roleRepository.findAll();
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    roles
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Override
    public RCCResponse<Object> detail(Long id) {
        try {
            var roleDetail = roleRepository.findById(id);
            if(roleDetail.isEmpty()){
                return ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        roleDetail
                );
            }
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    roleDetail
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> save(RoleRequest roleRequest) {
        try{
            Role mapRole = Role.builder()
                    .id(roleRequest.getId())
                    .roleName(roleRequest.getRoleName()).build();
            Role savedRole = roleRepository.save(mapRole);

            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    savedRole
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }

    @Transactional
    @Override
    public RCCResponse<Object> delete(Long id) {
        try {
            var roleDetail = roleRepository.findById(id);
            if(roleDetail.isEmpty()){
                return ResponseUtil.response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.DATA_NOT_FOUND,
                        ResponseCode.CommonEng.DATA_NOT_FOUND,
                        roleDetail
                );
            }
            roleRepository.delete(roleDetail.get());
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                    roleDetail
            );
        }catch (Exception e){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.ERROR,
                    ResponseCode.CommonEng.ERROR
            );
        }
    }
}
