package com.arcs.cibus.server.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    @Query ("SELECT p FROM cibus_payments p "
            + "WHERE (:payment is null or LOWER(p.payment) LIKE %:payment%) "
            + "AND (:description is null or LOWER(p.description) LIKE %:description%) "
            + "AND (:active is null or p.visible is :active) "
            + "ORDER BY p.payment"
    )
    Page<Payment> findAll(Pageable pageable,
                          @Param ("payment") String payment,
                          @Param ("description") String description,
                          @Param ("active") boolean active);

    @Query ("SELECT p FROM cibus_payments p "
            + "WHERE (:payment is null or LOWER(p.payment) LIKE %:payment%) "
            + "AND (:description is null or LOWER(p.description) LIKE %:description%) "
            + "ORDER BY p.payment"
    )
    Page<Payment> findAll(Pageable pageable,
                          @Param ("payment") String payment,
                          @Param ("description") String description);

    @Query ("SELECT p FROM cibus_payments p "
            + "WHERE p.visible is true "
            + "ORDER BY p.payment"
    )
    List<Payment> findAllVisible();

}
