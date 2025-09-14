package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>
{
    @Query("SELECT o FROM cibus_order o "
            + "WHERE (:cardOrderId is null or o.cardOrderId = :cardOrderId) "
            + "AND o.orderStatus = 'OPEN' "
    )
    List<Order> getOpenOrderById(@Param("cardOrderId") Long cardOrderId);

    @Query("SELECT o FROM cibus_order o "
            + "WHERE (:cardOrderId is null or o.cardOrderId = :cardOrderId) "
            + "AND (:tableId is null or o.tableId = :tableId) "
            + "AND o.orderStatus = 'OPEN' "
    )
    Order getOrderByCardOrderIdAndTableId(@Param("cardOrderId") Long cardOrderId,
                                          @Param("tableId") Long tableId);
}
