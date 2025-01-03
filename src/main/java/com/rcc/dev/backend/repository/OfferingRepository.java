package com.rcc.dev.backend.repository;

import com.rcc.dev.backend.dto.offering.OfferingChartDto;
import com.rcc.dev.backend.dto.offering.pojo.OfferingChartNamePojo;
import com.rcc.dev.backend.dto.offering.pojo.OfferingChartPojo;
import com.rcc.dev.backend.model.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
        @Query("SELECT new com.rcc.dev.backend.dto.offering.OfferingChartDto" +
                "(o.sabbathDate, oc.OfferingName, SUM(o.amount)) " +
                "FROM Offering o JOIN o.offeringCategory oc " +
                "GROUP BY o.sabbathDate, oc.OfferingName " +
                "ORDER BY o.sabbathDate")
        List<OfferingChartDto> findOfferingChartData();

        @Query("SELECT SUM(o.amount) FROM Offering o WHERE MONTH(o.sabbathDate) = :month AND YEAR(o.sabbathDate) = :year")
        BigDecimal totalOfferingInMonth(@Param("month") int month, @Param("year") int year);

        @Query("SELECT o.sabbathDate, SUM(o.amount) FROM Offering o GROUP BY o.sabbathDate")
        List<Object[]> totalOfferingBySabbath();

        @Query("SELECT o.offeringCategory.OfferingName, SUM(o.amount) FROM Offering o GROUP BY o.offeringCategory.OfferingName")
        List<Object[]> totalOfferingByCategory();

        @Query("SELECT SUM(o.amount) - :deductions FROM Offering o WHERE YEAR(o.sabbathDate) = :year")
        BigDecimal totalAfterDeductions(@Param("year") int year, @Param("deductions") BigDecimal deductions);

        @Query("SELECT o.offeringCategory.OfferingName, SUM(o.amount) FROM Offering o WHERE YEAR(o.sabbathDate) = :year GROUP BY o.offeringCategory.OfferingName")
        List<Object[]> totalOfferingInYearByCategory(@Param("year") int year);

        @Query("SELECT o.sabbathDate, SUM(o.amount) FROM Offering o WHERE o.offeringCategory.id = :categoryId " +
                "AND (:startDate IS NULL OR o.sabbathDate >= :startDate) " +
                "AND (:endDate IS NULL OR o.sabbathDate <= :endDate) GROUP BY o.sabbathDate")
        List<Object[]> filterByCategoryAndDateRange(@Param("categoryId") Long categoryId,
                                                    @Param("startDate") Date startDate,
                                                    @Param("endDate") Date endDate);


        @Query(value = "SELECT \n" +
                "    o.id AS id, \n" +
                "    o.sabbath_date AS SabbathDate, \n" +
                "    oc.offering_name AS OfferingName, \n" +
                "    SUM(o.amount) AS Amount \n" +
                "FROM \n" +
                "    offering o \n" +
                "JOIN \n" +
                "    offering_category oc \n" +
                "ON \n" +
                "    o.offering_category_id = oc.id \n" +
                "GROUP BY \n" +
                "    o.sabbath_date, oc.offering_name, o.id\n" +
                "order by \n" +
                "\to.sabbath_date asc", nativeQuery = true)
        List<OfferingChartPojo> getOfferingChartPojo();

        @Query(value = "" +
                "SELECT  \n" +
                "\to.id as id,\n" +
                "    oc.offering_name AS offeringName, \n" +
                "    o.sabbath_date as sabbathDate,\n" +
                "    SUM(o.amount) AS amount \n" +
                "FROM \n" +
                "    offering o \n" +
                "JOIN \n" +
                "    offering_category oc \n" +
                "ON \n" +
                "    o.offering_category_id = oc.id \n" +
                "WHERE \n" +
                "\tsabbath_date BETWEEN :startDate AND :endDate\n" +
                "GROUP BY \n" +
                "    oc.offering_name, o.sabbath_date, o.id\n" +
                "ORDER BY \n" +
                "    o.sabbath_date DESC;", nativeQuery = true)
        List<OfferingChartPojo> getOfferingChartPojoByMonth(
                @Param("startDate") Date startDate,
                @Param("endDate") Date endDate);

        @Query(value =
                "SELECT oc.offering_name AS offeringName, " +
                "SUM(o.amount) AS totalAmount " +
                "FROM " +
                "offering o " +
                "JOIN " +
                "offering_category oc " +
                "ON " +
                "o.offering_category_id = oc.id " +
                "GROUP BY " +
                "oc.offering_name " +
                "ORDER BY " +
                "oc.offering_name;", nativeQuery = true)
        List<OfferingChartNamePojo> getListChartOfferingName();
}
