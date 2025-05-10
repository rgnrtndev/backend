package com.rcc.dev.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table
public class Offering extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 5367855294632123393L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sabbath_date")
    private Date sabbathDate;

    @Column(name = "image_base64")
    private String imageBase64;

    @Column(name = "check_by")
    private Long checkBy;

    @Column(name = "check_date")
    private Long checkDate;

    @Column(name = "approve_by")
    private Long approveBy;

    @Column(name = "approve_date")
    private Date approveDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "offering_category_id", referencedColumnName = "id")
    @JsonBackReference
    private OfferingCategory offeringCategory;
}
