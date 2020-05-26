package com.arcs.cibus.server.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.ProductSku;
import com.arcs.cibus.server.repository.ProductSkuRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class ProductSkuService {
	
	@Autowired
	private ProductSkuRepository productSkuRepository;
	
	public Page<ProductSku> getAll(Long productId, int page, int quantity) throws Exception {
		Pageable paginacao = PageRequest.of(page, quantity);
		return productSkuRepository.findAllByProductId(productId, paginacao);
	}
	
	public ProductSku getById(Long productSkuId) throws ObjectNotFoundException {
		Optional<ProductSku> productSku = productSkuRepository.findById(productSkuId);
		return productSku.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + productSku + " não existe."));
	}
	
	public void delete(Long productSkuId) throws ConstraintViolationException, Exception {	
		ProductSku productSku = this.getById(productSkuId);
		productSkuRepository.delete(productSku);
	}
	
	public ProductSku save(ProductSku productSku) throws Exception {
		return productSkuRepository.save(productSku); 
	}
}
