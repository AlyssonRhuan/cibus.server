package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.SaleStatus;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public Page<Sale> getAll(int page, int quantity, String product, String date, String saleStatus) throws Exception {
		Pageable paginacao = PageRequest.of(page, quantity);
		return saleRepository.findFiltered(paginacao,
				StringUtils.isEmpty(product) ? null : product,
				StringUtils.isEmpty(date) ? null : date,
				StringUtils.isEmpty(saleStatus) ? null : saleStatus);
	}

	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}

	public List<Sale> saveSale(List<Sale> sales) {
		for(Sale sale : sales){
			if(sale.getSaleStatus() == null){
				sale.setSaleStatus(SaleStatus.PAID);
			}

			sale = saleRepository.save(sale);
		}

		return sales;
	}
}
