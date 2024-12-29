package com.rcc.dev.backend.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class RefreshTokenRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2318512993524353940L;

    private String userId;
    private String refreshToken;
}
