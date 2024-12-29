package com.rcc.dev.backend.service.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rcc.dev.backend.constant.CacheConstant;
import com.rcc.dev.backend.constant.ResponseCode;
import com.rcc.dev.backend.dto.auth.LoginRequest;
import com.rcc.dev.backend.dto.auth.LoginResponse;
import com.rcc.dev.backend.dto.auth.RefreshTokenRequest;
import com.rcc.dev.backend.dto.auth.RefreshTokenResponse;
import com.rcc.dev.backend.dto.response.RCCResponse;
import com.rcc.dev.backend.repository.UserRepository;
import com.rcc.dev.backend.service.auth.iservice.AuthService;
import com.rcc.dev.backend.util.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final AuthenticationUtils authenticationUtils;
    private final LoggerUtil loggerUtil;
    private final CacheUtil cacheUtil;

    @Override
    public RCCResponse<Object> login(HttpServletRequest httpServletRequest, LoginRequest loginRequest) {
        var dataUser = userRepository.findByUsernameAndIsDeletedFalse(loginRequest.getUsername());
        if(dataUser.isEmpty()){
            return ResponseUtil.response(
                    ResponseCode.ERROR_RESPONSE_CODE,
                    ResponseCode.CommonIdn.INCORRECT_USERNAME_PASSWORD,
                    ResponseCode.CommonEng.INCORRECT_USERNAME_PASSWORD
            );
        }

        String refreshToken = loggerUtil.getUid();
        String id = String.valueOf(dataUser.get().getId());
        Boolean isBoard = dataUser.get().getIsBoard();

        HashMap<String, Object> claimData = new HashMap<>();
        long portalTokenAge = 1800;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_TOKEN_AGE));
        long portalRefreshTokenAge = 2700;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_REFRESH_TOKEN_AGE));
        long tokenExpired = 1800000;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_TOKEN_EXPIRED));
        long refreshTokenExpired = 1800000;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_REFRESH_TOKEN_EXPIRED));

        String username = dataUser.get().getUsername();
        claimData.put("username", username);
        claimData.put("id", id);
        claimData.put("isBoard", isBoard);
        JWTUtils.setExpirationTime(tokenExpired);
        JWTUtils.setRefreshExpirationTime(refreshTokenExpired);

        String token = JWTUtils.generateToken(username, claimData);
        var response =LoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .username(username)
                .isBoard(isBoard)
                .build();

        cacheUtil.putCacheWithTTL(CacheConstant.CACHE_TOKEN, id, token, portalTokenAge);
        cacheUtil.putCacheWithTTL(CacheConstant.CACHE_REFRESH_TOKEN, id, claimData, portalRefreshTokenAge);


        return ResponseUtil.response(
                ResponseCode.SUCCESS_RESPONSE_CODE,
                ResponseCode.CommonIdn.LOGIN_SUCCESSFULLY,
                ResponseCode.CommonEng.LOGIN_SUCCESSFULLY,
                response
        );
    }

    @Override
    public RCCResponse<Object> logout(HttpServletRequest httpServletRequest) {
        var userData =authenticationUtils.validateAuthentication(httpServletRequest);
        cacheUtil.removeCache(CacheConstant.CACHE_TOKEN, userData.getId());
        cacheUtil.removeCache(CacheConstant.CACHE_REFRESH_TOKEN, userData.getId());
        return ResponseUtil.response(
                ResponseCode.SUCCESS_RESPONSE_CODE,
                ResponseCode.CommonIdn.LOGIN_SUCCESSFULLY,
                ResponseCode.CommonEng.LOGIN_SUCCESSFULLY
        );
    }

    @Override
    public RCCResponse<Object> refreshToken(HttpServletRequest httpServletRequest, RefreshTokenRequest refreshTokenRequest) {
        ObjectMapper mapper = new ObjectMapper();
        var cacheRefreshToken = cacheUtil.getCache(CacheConstant.CACHE_REFRESH_TOKEN, refreshTokenRequest.getUserId());
        if(Objects.isNull(cacheRefreshToken)){
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.SESSION_EXPIRED,
                    ResponseCode.CommonEng.SESSION_EXPIRED
            );
        }
        HashMap<String, Object> claimData = mapper.convertValue(cacheRefreshToken, HashMap.class);
        String id = claimData.get("id").toString();
        if (!claimData.get("refreshToken").equals(refreshTokenRequest.getRefreshToken())) {
            return ResponseUtil.response(
                    ResponseCode.SUCCESS_RESPONSE_CODE,
                    ResponseCode.CommonIdn.REFRESH_TOKEN_NOT_MATCH,
                    ResponseCode.CommonEng.REFRESH_TOKEN_NOT_MATCH
            );
        }
        String refreshToken = loggerUtil.getUid();
        claimData.put("refreshToken", refreshToken);
        String username = userRepository.findUsernameById(Long.valueOf(id));
        long portalTokenAge = 1800;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_TOKEN_AGE));
        long portalRefreshTokenAge = 2700;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_REFRESH_TOKEN_AGE));
        long tokenExpired = 1800000;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_TOKEN_EXPIRED));
        long refreshTokenExpired = 1800000;//Long.parseLong(generalParameterRepository.getValueWhereName(GeneralParameterConstant.PORTAL_REFRESH_TOKEN_EXPIRED));
        JWTUtils.setExpirationTime(tokenExpired);
        JWTUtils.setRefreshExpirationTime(refreshTokenExpired);
        String token = JWTUtils.generateToken(username, claimData);

        cacheUtil.removeCache(CacheConstant.CACHE_TOKEN, id);
        cacheUtil.removeCache(CacheConstant.CACHE_REFRESH_TOKEN, id);
        cacheUtil.putCacheWithTTL(CacheConstant.CACHE_TOKEN, id, token, portalTokenAge);
        cacheUtil.putCacheWithTTL(CacheConstant.CACHE_REFRESH_TOKEN, id, claimData, portalRefreshTokenAge);

        var responseToken = RefreshTokenResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();

        return ResponseUtil.response(
                ResponseCode.SUCCESS_RESPONSE_CODE,
                ResponseCode.CommonIdn.SESSION_EXPIRED,
                ResponseCode.CommonEng.SESSION_EXPIRED,
                responseToken
        );
    }
}
