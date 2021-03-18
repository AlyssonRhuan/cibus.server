package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.service.DashboardService;
import com.arcs.cibus.server.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sale")
public class SaleResource {

    @Autowired
    private SaleService saleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Sale>> getAll(int page, int quantity) throws Exception {
        Page<Sale> sales = saleService.getAll(page - 1, quantity);
        return ResponseEntity.ok(sales);
    }
}
