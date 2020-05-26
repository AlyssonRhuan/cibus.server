package com.arcs.cibus.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.ProductSku;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {

	Page<ProductSku> findAllByProductId(Long productId, Pageable pageable);
}
