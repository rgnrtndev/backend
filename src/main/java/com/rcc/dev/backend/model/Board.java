package com.rcc.dev.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long roleId;
    private Long galleryId;
    private String phoneNumber;
    private Date startPeriod;
    private Date endPeriod;
    private Date createdDate;
    private Long createdBy;
    private Date updatedDate;
    private Long updatedBy;
}