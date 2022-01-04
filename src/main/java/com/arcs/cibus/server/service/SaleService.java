package com.arcs.cibus.server.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.repository.SaleProductRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.serializer.CashSerializer;
import com.arcs.cibus.server.service.exceptions.DataException;


@Service
public class SaleService
{
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private ValidadeService validadeService;

    @Autowired
    private CashService cashService;

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

        Page<Sale> salePageable = null;
        if (saleStatus.equals(SaleStatus.BOUTH))
        {
            salePageable = saleRepository.findAll(paginacao, dateInitial, dateFinal);
        }
        else
        {
            salePageable = saleRepository.findAll(paginacao, dateInitial, dateFinal, saleStatus);
        }
        return salePageable;
    }

    public Sale saveSale(Sale sale) throws Exception
    {
        Sale saleToSave = Sale.builder()
                              .saleStatus(sale.getSaleStatus() != null ? sale.getSaleStatus() : SaleStatus.PAID)
                              .saleDate(new Date())
                              .client(sale.getClient())
                              .payment(sale.getPayment())
                              .build();

        saleRepository.save(saleToSave);

        for (SaleProduct saleProduct : sale.getSaleProducts())
        {
            saleProduct.setSale(saleToSave);
            saleProductRepository.save(saleProduct);
        }

        saleToSave.setSaleProducts(sale.getSaleProducts());

        validadeService.validateStock(saleToSave);

        if(sale.getPayment().getIsCashMoviment()){
            cashService.updateCashValue(sale);
        }

        return saleToSave;
    }
}
