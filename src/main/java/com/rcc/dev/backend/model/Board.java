package com.rcc.dev.backend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table
public class Board extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 5176459258825566124L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long roleId;
    private Long galleryId;
    private String phoneNumber;
    private Date startPeriod;
    private Date endPeriod;
}