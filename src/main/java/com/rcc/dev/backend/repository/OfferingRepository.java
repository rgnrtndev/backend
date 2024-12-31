package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.model.Offering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
}
