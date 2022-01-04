package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM cibus_sales s "
//            + "INNER JOIN s.saleProducts sp "
            + "WHERE (:dateInitial is null and :dateFinal is null or s.saleDate BETWEEN :dateInitial AND :dateFinal)"
            + "  AND (:status is null or s.saleStatus = :status)"
    )
    Page<Sale> findAll(Pageable pageable,
                        @Param("dateInitial") Date dateInitial,
                        @Param("dateFinal") Date dateFinal,
                        @Param("status") SaleStatus status);


    @Query("SELECT s FROM cibus_sales s "
//            + "INNER JOIN s.saleProducts sp "
            + "WHERE (:dateInitial is null and :dateFinal is null or s.saleDate BETWEEN :dateInitial AND :dateFinal)"
    )
    Page<Sale> findAll(Pageable pageable,
                       @Param("dateInitial") Date dateInitial,
                       @Param("dateFinal") Date dateFinal);

    @Query("SELECT s FROM cibus_sales s "
            + "WHERE s.saleDate BETWEEN :dateInitial AND :dateFinal"
    )
    List<Sale> findAllByPeriod(@Param("dateInitial") Date dateInitial,
                               @Param("dateFinal") Date dateFinal);
}
