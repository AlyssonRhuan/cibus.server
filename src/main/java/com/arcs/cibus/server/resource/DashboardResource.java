package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.DashboardPeriod;
import com.arcs.cibus.server.service.DashboardService;
import com.arcs.cibus.server.service.ProductService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardResource {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value="/{period}", method = RequestMethod.GET)
    public ResponseEntity<Dashboard> getData(@PathVariable DashboardPeriod period) throws Exception {
        Dashboard darshboard = dashboardService.getData(period);
        return ResponseEntity.ok(darshboard);
    }
}
