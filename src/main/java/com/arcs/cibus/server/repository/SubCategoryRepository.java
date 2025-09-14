package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("SELECT c FROM cibus_subcategories c "
            + "WHERE (:name is null or LOWER(c.name) LIKE %:name%) "
            + "AND (:description is null or LOWER(c.description) LIKE %:description%) "
            + "AND (:active is null or c.active is :active) "
            + "ORDER BY c.name"
    )
    Page<SubCategory> findAll(Pageable pageable,
                           @Param("name") String name,
                           @Param("description") String description,
                           @Param("active") boolean active);

    @Query("SELECT c FROM cibus_subcategories c "
            + "WHERE (:name is null or LOWER(c.name) LIKE %:name%) "
            + "AND (:description is null or LOWER(c.description) LIKE %:description%) "
            + "ORDER BY c.name"
    )
    Page<SubCategory> findAll(Pageable pageable,
                           @Param("name") String name,
                           @Param("description") String description);

}
