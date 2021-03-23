package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sale")
public class SaleResource {

    @Autowired
    private SaleService saleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Sale>> getAll(int page, int quantity, String product, String date, String saleStatus)
            throws Exception {
        Page<Sale> sales = saleService.getAll(page - 1, quantity, product, date, saleStatus);
        return ResponseEntity.ok(sales);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sale> saveSale(Sale sale) throws Exception {
        saleService.saveSale(sale);
        return ResponseEntity.ok(sale);
    }

    @RequestMapping(value="/all", method = RequestMethod.POST)
    public ResponseEntity<List<Sale>> saveSales(@RequestBody ArrayList<Sale> sales) throws Exception {
        saleService.saveSale(sales);
        return ResponseEntity.ok(sales);
    }
}
