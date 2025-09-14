package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.ProductVariations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariationsRepository extends JpaRepository<ProductVariations, Long> {

//    @Query("SELECT pv FROM cibus_products_variations pv "
//            + "WHERE (:productId is null or pv.productId = :productId) "
//    )
    List<ProductVariations> getAllByProductId(Long productId);

}