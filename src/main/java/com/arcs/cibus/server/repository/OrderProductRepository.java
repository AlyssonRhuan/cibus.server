package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Order;
import com.arcs.cibus.server.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>
{

}
