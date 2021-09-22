package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.DashboardPeriod;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CashRepository;
import com.arcs.cibus.server.repository.CategoryRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import com.arcs.cibus.server.service.utils.DateUtils;

import lombok.Builder;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@Service
public class DashboardService
{

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CashRepository cashRepository;

    public Dashboard getData(DashboardPeriod period)
    {
        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);
        Dashboard dashboard = Dashboard.builder()
                                       .salesTotal(new BigDecimal(0))
                                       .orders(0)
                                       .cashs(0)
                                       .build();

        dashboard.setCashs(cashRepository.CountOpenCashs());

        for (Sale sale : sales)
        {
            for (SaleProduct saleProduct : sale.getSaleProducts())
            {
                BigDecimal pricaTotal = dashboard.getSalesTotal().add(saleProduct.getPrice().multiply(new BigDecimal(saleProduct.getQuantity())));
                dashboard.setSalesTotal(pricaTotal);

                dashboard.setOrders(dashboard.getOrders() + 1);

                if (dashboard.getQuantityProducts().containsKey(saleProduct.getProduct().getName()))
                {
                    dashboard.getQuantityProducts().put(saleProduct.getProduct().getName(), saleProduct.getQuantity() + dashboard.getQuantityProducts().get(saleProduct.getProduct().getName()).longValue());
                }
                else
                {
                    dashboard.getQuantityProducts().put(saleProduct.getProduct().getName(), saleProduct.getQuantity());
                }

                for (Category category : saleProduct.getProduct().getCategorys())
                {
                    if (dashboard.getPercentCategories().containsKey(category.getName()))
                    {
                        dashboard.getPercentCategories().put(category.getName(), saleProduct.getQuantity() + dashboard.getPercentCategories().get(category.getName()).doubleValue());
                    }
                    else
                    {
                        dashboard.getPercentCategories().put(category.getName(), saleProduct.getQuantity().doubleValue());
                    }
                }
            }
        }

        updateCategoriesPercent(dashboard);

        return dashboard;
    }

    private void updateCategoriesPercent(Dashboard dashboard)
    {
        int total = 0;

        for (Map.Entry<String, Long> t : dashboard.getQuantityProducts().entrySet())
        {
            total = total + t.getValue().intValue();
        }

        for (Map.Entry<String, Double> t : dashboard.getPercentCategories().entrySet())
        {
            Double quantity = t.getValue() / total;
            dashboard.getPercentCategories().put(t.getKey(), quantity);
        }
    }
}
