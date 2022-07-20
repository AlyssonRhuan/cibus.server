package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.*;
import com.arcs.cibus.server.domain.enums.ReportPeriod;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CashRepository;
import com.arcs.cibus.server.repository.ReportRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import com.arcs.cibus.server.service.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ReportService
{
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CashRepository cashRepository;

    public List<Report> getAll(ReportPeriod period) throws Exception
    {
        return reportRepository.findAll();
    }

    public List<Report> getAllDashboard(ReportPeriod period) throws Exception
    {
        return reportRepository.findAll();
    }

    public Report getValueInSales(Long reportId, ReportPeriod period) throws Exception {
        Report valueInSales = reportRepository.findById(reportId).get();
        valueInSales.setTipoSerializer(TipoSerializer.FULL);

        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);

        BigDecimal priceTotal = new BigDecimal(0);

        for (Sale sale : sales)
        {
            for (SaleProduct saleProduct : sale.getSaleProducts())
            {
                priceTotal = priceTotal.add(saleProduct.getPrice().multiply(new BigDecimal(saleProduct.getQuantity())));
            }
        }
        valueInSales.getData().put("priceTotal", priceTotal.toString());

        return valueInSales;
    }

    public Report getOpenCashs(Long reportId, ReportPeriod period) {
        Report openCashs = reportRepository.findById(reportId).get();
        openCashs.setTipoSerializer(TipoSerializer.FULL);

        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        Integer cashs = cashRepository.CountOpenCashs();
        openCashs.getData().put("openCashs", cashs.toString());

        return openCashs;
    }

    public Report getOrdersCount(Long reportId, ReportPeriod period) {
        Report ordersCount = reportRepository.findById(reportId).get();
        ordersCount.setTipoSerializer(TipoSerializer.FULL);

        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);

        int qtdOrders = 0;

        for (Sale sale : sales)
        {
            qtdOrders += sale.getSaleProducts().size();
        }

        ordersCount.getData().put("ordersCount", qtdOrders + "");

        return ordersCount;
    }

    public Report getPercentCategories(Long reportId, ReportPeriod period) {
        Report percentCategories = reportRepository.findById(reportId).get();
        percentCategories.setTipoSerializer(TipoSerializer.FULL);

        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);

        for (Sale sale : sales)
        {
            for (SaleProduct saleProduct : sale.getSaleProducts())
            {
                for (Category category : saleProduct.getProduct().getCategorys())
                {
                    if (percentCategories.getData().containsKey(category.getName()))
                    {
                        percentCategories.getData().put(category.getName(), saleProduct.getQuantity() + percentCategories.getData().get(category.getName()).toString());
                    }
                    else
                    {
                        percentCategories.getData().put(category.getName(), saleProduct.getQuantity().toString());
                    }
                }
            }
        }

        return percentCategories;
    }

    public Report getQuantityProducts(Long reportId, ReportPeriod period) {
        Report quantityProducts = reportRepository.findById(reportId).get();
        quantityProducts.setTipoSerializer(TipoSerializer.FULL);

        Date dateInitial = DateUtils.defineInitialDate(period);
        Date dateFinal = DateUtils.defineFinalDate(period);

        List<Sale> sales = saleRepository.findAllByPeriod(dateInitial, dateFinal);

        for (Sale sale : sales)
        {
            for (SaleProduct saleProduct : sale.getSaleProducts())
            {
                if (quantityProducts.getData().containsKey(saleProduct.getProduct().getName()))
                {
                    quantityProducts.getData().put(saleProduct.getProduct().getName(), saleProduct.getQuantity() + quantityProducts.getData().get(saleProduct.getProduct().getName()));
                }
                else
                {
                    quantityProducts.getData().put(saleProduct.getProduct().getName(), saleProduct.getQuantity().toString());
                }
            }
        }

        return quantityProducts;
    }
}
