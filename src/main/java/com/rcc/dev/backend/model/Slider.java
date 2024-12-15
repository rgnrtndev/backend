package com.rcc.dev.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slider")
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "img_base_64")
    private String imgBase64;

    @CreationTimestamp
    private Date createdDate;

    private Long createdBy;

    @UpdateTimestamp
    private Date updatedDate;

    private Long updatedBy;

    private Boolean isDeleted;
}
