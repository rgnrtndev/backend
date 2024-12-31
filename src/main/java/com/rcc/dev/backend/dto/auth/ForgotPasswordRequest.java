package com.rcc.dev.backend.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ForgotPasswordRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6198462651658950331L;

    private String email;
}
