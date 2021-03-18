package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import lombok.Builder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;


@Service
public class DashboardService {

	@Autowired
	private SaleRepository saleRepository;

	public Dashboard getData() {
		List<Sale> sales = saleRepository.findAll();
		Dashboard dashboard = Dashboard.builder()
				.salesTotal(new BigDecimal(0))
				.ordersClosed(0)
				.ordersOpenned(0)
				.build();



		for(Sale sale : sales){
			BigDecimal pricaTotal = dashboard.getSalesTotal().add(sale.getPrice()).multiply(new BigDecimal(sale.getQuantity()));
			dashboard.setSalesTotal(pricaTotal);

			if(sale.getSaleStatus().equals(SaleStatus.ORDER)){
				dashboard.setOrdersOpenned(dashboard.getOrdersOpenned() + 1);
			}

			if(sale.getSaleStatus().equals(SaleStatus.PAID)){
				dashboard.setOrdersClosed(dashboard.getOrdersClosed() + 1);
			}

			if(dashboard.getQuantityProducts().containsKey(sale.getProduct().getName())){
				dashboard.getQuantityProducts().put(sale.getProduct().getName(), sale.getQuantity() + dashboard.getQuantityProducts().get(sale.getProduct().getName()).longValue());
			}
			else{
				dashboard.getQuantityProducts().put(sale.getProduct().getName(), sale.getQuantity());
			}

			if(dashboard.getPercentCategories().containsKey(sale.getCategory().getName())){
				dashboard.getPercentCategories().put(sale.getCategory().getName(), sale.getQuantity() + dashboard.getPercentCategories().get(sale.getCategory().getName()).doubleValue());
			}
			else{
				dashboard.getPercentCategories().put(sale.getCategory().getName(), sale.getQuantity().doubleValue());
			}
		}

		updateCategoriesPercent(dashboard);

		return dashboard;
	}

	private void updateCategoriesPercent(Dashboard dashboard) {
		int totalOrders = dashboard.getOrdersClosed() + dashboard.getOrdersOpenned();

		for(Map.Entry<String, Double> t : dashboard.getPercentCategories().entrySet()){
			Double quantity = t.getValue() / totalOrders;
			dashboard.getPercentCategories().put(t.getKey(), quantity);
		}
	}
}
