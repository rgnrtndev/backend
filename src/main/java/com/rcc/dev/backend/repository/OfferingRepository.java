package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.dto.offering.OfferingChartDto;
import com.rcc.dev.backend.model.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
        @Query("SELECT new com.rcc.dev.backend.dto.offering.OfferingChartDto" +
                "(o.sabbathDate, oc.OfferingName, SUM(o.amount)) " +
                "FROM Offering o JOIN o.offeringCategory oc " +
                "GROUP BY o.sabbathDate, oc.OfferingName " +
                "ORDER BY o.sabbathDate")
        List<OfferingChartDto> findOfferingChartData();

}
