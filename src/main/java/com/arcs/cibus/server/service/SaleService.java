package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.DashboardPeriod;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.DataException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import com.arcs.cibus.server.service.utils.DateUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public Page<Sale> getAll(int page, int quantity, String product, String date, SaleStatus saleStatus) throws Exception {
		Pageable paginacao = PageRequest.of(page, quantity);

		if(!date.isEmpty()) {
			try {
				new SimpleDateFormat("dd/MM/yyyy").parse(date);
			} catch (Exception e) {
				throw new DataException("A data não está no padrão correto.");
			}
		}

		if(product.isEmpty()) product = null;

		Date dateInitial = date.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date + " 00:00:00");
		Date dateFinal = date.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date + " 23:59:59");


		if(saleStatus.equals(SaleStatus.BOUTH)){
			return saleRepository.findAll(paginacao, product, dateInitial, dateFinal);
		}
		else{
			return saleRepository.findAll(paginacao, product, dateInitial, dateFinal, saleStatus);
		}
	}

	public Sale saveSale(Sale sale) {
		return saleRepository.save(sale);
	}

	public List<Sale> saveSale(List<Sale> sales) {
		for(Sale sale : sales){
			if(sale.getSaleStatus() == null){
				sale.setSaleStatus(SaleStatus.PAID);
			}

			sale.setSaleDate(new Date());
			sale = saleRepository.save(sale);
		}

		return sales;
	}
}
