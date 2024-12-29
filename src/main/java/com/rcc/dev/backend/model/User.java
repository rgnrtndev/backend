package com.rcc.dev.backend.model;

import com.rcc.dev.backend.constant.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -2559011966610557668L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "is_board")
    private Boolean isBoard;

    @Column(name = "last_success_login_date")
    private Date lastSuccessLoginDate;

    @Column(name = "last_failed_login_date")
    private Date lastFailedLoginDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
