package com.rcc.dev.backend.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class LoginRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -5237235898592609161L;
    private String username;
    private String password;
}
