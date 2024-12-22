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
@Table
@Entity
public class Announcement extends BaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = -2896333516764827211L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(name = "gallery_id")
    private Long galleryId;
}
