package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.categorys c "
            + "WHERE (:categoryId is null or c.id = :categoryId) "
            + "AND p.visible is true "
            + "AND c.active is true "
            + "AND p.stockQuantity > 0 "
            + "ORDER BY p.name"
    )
    Page<Product> getAllByCategory(Pageable pageable,
                            @Param("categoryId") Long categoryId);

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.categorys c "
            + "WHERE (:name is null or p.name LIKE %:name%) "
            + "AND (:category is null or c.id = :category) "
            + "AND (:active is null or p.visible is :active) "
            + "ORDER BY p.name"
    )
    Page<Product> findAll(Pageable pageable,
                           @Param("name") String name,
                           @Param("category") Long categoryId,
                           @Param("active") boolean active);

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.categorys c "
            + "WHERE (:name is null or p.name LIKE %:name%) "
            + "AND (:category is null or c.id = :category) "
            + "ORDER BY p.name"
    )
    Page<Product> findAll(Pageable pageable,
                          @Param("name") String name,
                          @Param("category") Long categoryId);
}