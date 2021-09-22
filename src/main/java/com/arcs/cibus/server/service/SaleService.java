package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Payment;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.repository.SaleProductRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.DataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class SaleService
{
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private ValidadeService validadeService;

    public Page<Sale> getAll(int page, int quantity, String product, String date, SaleStatus saleStatus) throws Exception
    {
        Pageable paginacao = PageRequest.of(page, quantity);

        if (!date.isEmpty())
        {
            try
            {
                new SimpleDateFormat("dd/MM/yyyy").parse(date);
            }
            catch (Exception e)
            {
                throw new DataException("A data não está no padrão correto.");
            }
        }

        if (product.isEmpty())
        {
            product = null;
        }

        Date dateInitial = date.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date + " 00:00:00");
        Date dateFinal = date.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(date + " 23:59:59");

        if (saleStatus.equals(SaleStatus.BOUTH))
        {
            return saleRepository.findAll(paginacao, dateInitial, dateFinal);
        }
        else
        {
            return saleRepository.findAll(paginacao, dateInitial, dateFinal, saleStatus);
        }
    }

    public Sale saveSale(Sale sale) throws Exception
    {
        if (sale.getSaleStatus() == null)
        {
            sale.setSaleStatus(SaleStatus.PAID);
        }
        sale.setSaleDate(new Date());

        sale = saleRepository.save(sale);
        saleProductRepository.saveAll(sale.getSaleProducts());

        validadeService.validateStock(sale);
        return sale;
    }
}
