package com.arcs.cibus.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.subCategories c "
            + "WHERE (:subCategoryId is null or c.id = :subCategoryId) "
            + "AND p.visible is true "
            + "AND c.active is true "
            + "AND p.stockQuantity > 0 "
            + "AND is_active IS NOT FALSE "
            + "ORDER BY p.name"
    )
    List<Product> getAllByCategory(@Param("subCategoryId") Long subCategoryId);

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.subCategories c "
            + "WHERE (:name is null or LOWER(p.name) LIKE %:name%) "
            + "AND (:subCategoryId is null or c.id = :subCategoryId) "
            + "AND (:active is null or p.visible is :active) "
            + "AND is_active IS NOT FALSE "
            + "ORDER BY p.name"
    )
    Page<Product> findAll(Pageable pageable,
                           @Param("name") String name,
                           @Param("subCategoryId") Long subCategoryId,
                           @Param("active") boolean active);

    @Query("SELECT p FROM cibus_products p "
            + "INNER JOIN p.subCategories c "
            + "WHERE (:name is null or LOWER(p.name) LIKE %:name%) "
            + "AND (:subCategoryId is null or c.id = :subCategoryId) "
            + "AND is_active IS NOT FALSE "
            + "ORDER BY p.name"
    )
    Page<Product> findAll(Pageable pageable,
                          @Param("name") String name,
                          @Param("subCategoryId") Long subCategoryId);
}