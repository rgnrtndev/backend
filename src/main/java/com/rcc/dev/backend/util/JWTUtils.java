package com.rcc.dev.backend.util;

import com.rcc.dev.backend.constant.ApiConstant;
import com.rcc.dev.backend.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;

import static java.util.Collections.emptyList;

@Slf4j
@Component
public class JWTUtils {

    public static KeyPair RSA_KEY = Jwts.SIG.RS512.keyPair().build();

    public static long EXPIRATIONTIME = Long.parseLong("900000");
    public static long REFRESH_EXPIRATIONTIME = Long.parseLong("3600000");

    @Value("${rcc.properties.data.expire-token-ms:900000}")
    public static void setExpirationTime(long getExpirationTime) {
        JWTUtils.EXPIRATIONTIME = getExpirationTime;
    }

    @Value("${ist.properties.data.expire-refresh-token-ms:3600000}")
    public static void setRefreshExpirationTime(long getRefreshExpirationtime) {
        JWTUtils.REFRESH_EXPIRATIONTIME = getRefreshExpirationtime;
    }

    public static void setRsaKey(KeyPair getKeyPair) {
        JWTUtils.RSA_KEY = getKeyPair;
    }

    static final String SECRET = "P+9QqFqgpNr+EqDDY4FX8vwEOMQ1xrKjCs0liBa90WaQ1OoXB6EcedxumDL5O0XmiIsgkxjU8yZcJ6sdpVBFXg==";
    public static final String TOKEN_PREFIX = "Bearer";


    public static String generateToken(String username, HashMap<String, Object> claimData) {
        HashMap<String, Object> wrappedClaim = new HashMap<>();
        wrappedClaim.put("userLogin", claimData);

        String JWT = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .claims(wrappedClaim)
                .expiration(new Date(Long.sum(JWTUtils.EXPIRATIONTIME, System.currentTimeMillis())))
                .signWith(RSA_KEY.getPrivate(), Jwts.SIG.RS512)
                .compact();
        try {
            JWT = AESCrypto.encrypt(JWT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return JWT;
    }

    public static <T> T claimObjectValue(HttpServletRequest request, String key, Class<T> clazz) {
        var claimData = claimValue(request, key);
        return JsonConverterUtil.fromObject(claimData, clazz);
    }

    public static Object claimValue(HttpServletRequest request, String key) {
        var token = getToken(request);
        if (token == null) {
            throw new RuntimeException();
        }
        return claimValue(token, key);
    }

    public static Object claimValue(String token, String key) {
        try {
            if (token == null) return null;

            token = AESCrypto.decrypt(token.trim());
            var claims = Jwts.parser()
                    .verifyWith(RSA_KEY.getPublic())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims != null ? claims.get(key) : null;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new RuntimeException("Invalid Token");
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            return e.getClaims().get(key);
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw new RuntimeException("Unsupported Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw new RuntimeException("Token claims is empty");
        }
    }

    @Deprecated
    public static Object getUserLoginDto(HttpServletRequest request) {
        try {
            var token = getToken(request);
            if (token == null) return null;

            token = AESCrypto.decrypt(token.trim());
            var claims = Jwts.parser()
                    .verifyWith(RSA_KEY.getPublic())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            return claims != null ? JsonConverterUtil.fromObject(claims.get(ApiConstant.KEY_CLAIM_USER_LOGIN), User.class) : null;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new RuntimeException("Invalid Token");
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw new RuntimeException("Token Expired");
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            throw new RuntimeException("Unsupported Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            throw new RuntimeException("Token claims is empty");
        }catch (SignatureException e) {
            log.error("JWT signature does not match: {}", e.getMessage());
            throw new RuntimeException("Invalid signature");
        }
    }

    public static String getToken(HttpServletRequest request) {
        var token = request.getHeader(ApiConstant.HEADER_STRING_AUTH);
        if (token == null) return null;
        return token.replace(TOKEN_PREFIX, "");
    }
}
