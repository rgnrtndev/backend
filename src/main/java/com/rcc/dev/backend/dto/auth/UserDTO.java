package com.rcc.dev.backend.dto.auth;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5935992049462219989L;

    private Long id;
    private String username;
    private Boolean isBoard;
    private String refreshToken;
}
