package com.rcc.dev.backend.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class RefreshTokenResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 7378267112075675266L;
    private String token;
    private String refreshToken;
}
