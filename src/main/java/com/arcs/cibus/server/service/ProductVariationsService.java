package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.ProductVariations;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.repository.ProductRepository;
import com.arcs.cibus.server.repository.ProductVariationsRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductVariationsService {

	@Autowired
	private ProductVariationsRepository productVariationsRepository;
	
	public List<ProductVariations> getByProductId(Long productId) throws ObjectNotFoundException {
		return productVariationsRepository.getAllByProductId(productId);
	}
	
	public void delete(Long productVariationsId) throws Exception {
		productVariationsRepository.deleteById(productVariationsId);
	}

	public ProductVariations save(ProductVariations productVariations) throws Exception {
		return productVariationsRepository.save(productVariations);
	}

	public ProductVariations getById(Long productVariationsId) throws Exception {
		return productVariationsRepository.findById(productVariationsId)
				.orElseThrow(() -> new ObjectNotFoundException("ProductVariations not found with id: " + productVariationsId));
	}
}
