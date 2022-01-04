package com.arcs.cibus.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.service.SaleService;

@RestController
@RequestMapping (value = "/sale")
public class SaleResource
{

    @Autowired
    private SaleService saleService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<Page<Sale>> getAll(int page, int quantity, String product, String date, SaleStatus saleStatus)
            throws Exception
    {
        Page<Sale> sales = saleService.getAll(page - 1, quantity, product, date, saleStatus);
        return ResponseEntity.ok(sales);
    }

    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Sale> saveSale(@RequestBody Sale sale) throws Exception
    {
        sale = saleService.saveSale(sale);
        return ResponseEntity.ok(sale);
    }
}
