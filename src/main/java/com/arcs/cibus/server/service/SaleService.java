package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public Page<Sale> getAll(int page, int quantity) throws Exception {
		Pageable paginacao = PageRequest.of(page, quantity);
		return saleRepository.findAll(paginacao);
	}
}
