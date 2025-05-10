package com.rcc.dev.backend.util;

import com.rcc.dev.backend.constant.ApiConstant;
import com.rcc.dev.backend.constant.CacheConstant;
import com.rcc.dev.backend.dto.auth.UserDTO;
import com.rcc.dev.backend.exception.UnauthorizedException;
import com.rcc.dev.backend.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationUtils {
    private final CacheUtil cacheUtil;

    public UserDTO validateAuthentication(HttpServletRequest httpServletRequest) {
        if(Objects.isNull(JWTUtils.getToken(httpServletRequest))){
            throw new UnauthorizedException("Must Be Login First");
        }
        var userData = JWTUtils.claimObjectValue(httpServletRequest, ApiConstant.KEY_CLAIM_USER_LOGIN, UserDTO.class);

        if (Objects.nonNull(userData.getId())) {
            String cacheToken = cacheUtil.getCacheString(CacheConstant.CACHE_TOKEN, userData.getId());
            String cacheRefreshToken = cacheUtil.getCacheString(CacheConstant.CACHE_REFRESH_TOKEN, userData.getId());

            if (StringUtils.isEmpty(cacheToken) || StringUtils.isEmpty(cacheRefreshToken)) {
                throw new UnauthorizedException("Token Expired");
            }
        } else {
            throw new UnauthorizedException("Token Expired");
        }
        return userData;
    }
}
