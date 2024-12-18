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
public class Gallery extends BaseDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 3957320758360233580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String imageBase64;
    private String description;
    private Long categoryId;
}
