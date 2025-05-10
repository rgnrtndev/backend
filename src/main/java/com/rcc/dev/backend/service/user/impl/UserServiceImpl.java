package com.rcc.dev.backend.service.user.impl;

import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.dto.user.UserRequestDto;
import com.rcc.dev.backend.model.User;
import com.rcc.dev.backend.repository.UserRepository;
import com.rcc.dev.backend.service.user.iservice.UserService;
import com.rcc.dev.backend.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<RCCResponse<Object>> findAll(HttpServletRequest httpServletRequest) {
        try {
            var users = userRepository.findAll();

            return ResponseEntity
                    .ok(ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                    ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA,
                    users
            ));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                    ResponseUtil.response(
                            ResponseCode.SUCCESS_RESPONSE_CODE,
                            ResponseCode.CommonIdn.SUCCESS_GET_ALL_DATA,
                            ResponseCode.CommonEng.SUCCESS_GET_ALL_DATA
            ));
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> detail(HttpServletRequest httpServletRequest, Long id) {
        try {

            var user = userRepository.findById(id);
            if(user.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseUtil.response(
                                ResponseCode.SUCCESS_RESPONSE_CODE,
                                ResponseCode.CommonIdn.DATA_NOT_FOUND,
                                ResponseCode.CommonEng.DATA_NOT_FOUND
                        ));
            }
            return ResponseEntity
                    .ok(ResponseUtil.response(
                            ResponseCode.SUCCESS_RESPONSE_CODE,
                            ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                            ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                            user.get()
                    ));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil
                            .response(
                                    ResponseCode.SUCCESS_RESPONSE_CODE,
                                    ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                                    ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL
                            )
                    );
        }
    }

    @Override
    public ResponseEntity<RCCResponse<Object>> update(HttpServletRequest httpServletRequest, UserRequestDto userRequestDto) {
        User user;
        try {

            if(Objects.isNull(userRequestDto.getId()) || userRequestDto.getId().equals(0L)){
                user = User.builder()
                        .email(userRequestDto.getEmail())
                        .fullName(userRequestDto.getFullName())
                        .username(userRequestDto.getUsername())
                        .isBoard(userRequestDto.getIsBoard())
                        .password(userRequestDto.getPassword())
                        .isDeleted(false)
                        .phoneNumber(userRequestDto.getPhoneNumber())
                        .build();
            }else{
                var userOpt = userRepository.findById(userRequestDto.getId());
                if(userOpt.isEmpty()){
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(ResponseUtil.response(
                            ResponseCode.SUCCESS_RESPONSE_CODE,
                            ResponseCode.CommonIdn.DATA_NOT_FOUND,
                            ResponseCode.CommonEng.DATA_NOT_FOUND
                    ));
                }
                user = userOpt.get();
                user = User.builder()
                        .email(userRequestDto.getEmail())
                        .fullName(userRequestDto.getFullName())
                        .username(userRequestDto.getUsername())
                        .isBoard(userRequestDto.getIsBoard())
                        .password(userRequestDto.getPassword())
                        .isDeleted(userRequestDto.getIsDeleted())
                        .phoneNumber(userRequestDto.getPhoneNumber())
                        .build();
            }
            user = userRepository.save(user);
            return ResponseEntity
                    .ok(ResponseUtil
                            .response(
                        ResponseCode.SUCCESS_RESPONSE_CODE,
                        ResponseCode.CommonIdn.SUCCESS_GET_DATA_DETAIL,
                        ResponseCode.CommonEng.SUCCESS_GET_DATA_DETAIL,
                        user
            ));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtil.response(
                            ResponseCode.SUCCESS_RESPONSE_CODE,
                            ResponseCode.CommonIdn.ERROR,
                            ResponseCode.CommonEng.ERROR)
                    );
        }
    }
}
