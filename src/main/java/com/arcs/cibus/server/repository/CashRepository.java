package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.User;
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
public interface CashRepository extends JpaRepository<Cash, Long> {

    @Query("SELECT c FROM cibus_cashs c "
            + "WHERE (:user is null or c.user.name LIKE %:user%) "
            + "  AND (:openDate is null or c.openDate = :openDate)"
            + "  AND (:closeDate is null or c.closeDate = :closeDate)"
            + "  AND (:description is null or c.description LIKE %:description%)"
    )
    Page<Cash> findAll(Pageable pageable,
                       @Param("user") String user,
                       @Param("description") String description,
                       @Param("openDate") Date openDate,
                       @Param("closeDate") Date closeDate);
}
