package com.arcs.cibus.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.SaleProduct;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Long>
{

}
