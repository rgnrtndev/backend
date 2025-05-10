package com.rcc.dev.backend.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class LoginResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7801533004700393621L;
    private String token;
    private String username;
    private String refreshToken;
    private Boolean isBoard;
}
