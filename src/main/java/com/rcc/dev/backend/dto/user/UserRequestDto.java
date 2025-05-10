package com.rcc.dev.backend.dto.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8882738151459013860L;

    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String token;
    private Boolean isBoard;
    private Date lastSuccessLoginDate;
    private Date lastFailedLoginDate;
    private Boolean isDeleted;
}
