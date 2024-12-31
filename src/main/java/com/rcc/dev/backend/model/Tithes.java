package com.rcc.dev.backend.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Tithes extends BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = -4553399600462703005L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private Date sabbathDate;
}
