package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM cibus_sales s "
            + "WHERE (:product is null or s.product.name = :product) "
            + "  AND (:date is null or s.saleDate = :date)"
            + "  AND (:saleStatus is null or s.saleStatus = :saleStatus)"
    )
    Page<Sale> findFiltered(Pageable pageable,
                            @Param("product") String product,
                            @Param("date") String date,
                            @Param("saleStatus") String saleStatus);
}
