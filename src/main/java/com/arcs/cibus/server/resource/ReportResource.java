package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Report;
import com.arcs.cibus.server.domain.enums.ReportPeriod;
import com.arcs.cibus.server.service.CashService;
import com.arcs.cibus.server.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportResource {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/all/{period}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getAll(@PathVariable ReportPeriod period) throws Exception {
        List<Report> reports = reportService.getAll(period);
        return ResponseEntity.ok(reports);
    }

    @RequestMapping(value = "/dashboard/{period}", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getAllDashboard(@PathVariable ReportPeriod period) throws Exception {
        List<Report> reports = reportService.getAllDashboard(period);
        return ResponseEntity.ok(reports);
    }

    @RequestMapping(value = "/valueInSales/{reportId}/{period}", method = RequestMethod.GET)
    public ResponseEntity<Report> getValueInSales(@PathVariable Long reportId, @PathVariable ReportPeriod period) throws Exception {
        Report valueInSales = reportService.getValueInSales(reportId, period);
        return ResponseEntity.ok(valueInSales);
    }

    @RequestMapping(value = "/openCashs/{reportId}/{period}", method = RequestMethod.GET)
    public ResponseEntity<Report> getOpenCashs(@PathVariable Long reportId, @PathVariable ReportPeriod period) throws Exception {
        Report valueInSales = reportService.getOpenCashs(reportId, period);
        return ResponseEntity.ok(valueInSales);
    }

    @RequestMapping(value = "/ordersCount/{reportId}/{period}", method = RequestMethod.GET)
    public ResponseEntity<Report> getOrdersCount(@PathVariable Long reportId, @PathVariable ReportPeriod period) throws Exception {
        Report valueInSales = reportService.getOrdersCount(reportId, period);
        return ResponseEntity.ok(valueInSales);
    }

    @RequestMapping(value = "/percentCategories/{reportId}/{period}", method = RequestMethod.GET)
    public ResponseEntity<Report> getPercentCategories(@PathVariable Long reportId, @PathVariable ReportPeriod period) throws Exception {
        Report valueInSales = reportService.getPercentCategories(reportId, period);
        return ResponseEntity.ok(valueInSales);
    }

    @RequestMapping(value = "/quantityProducts/{reportId}/{period}", method = RequestMethod.GET)
    public ResponseEntity<Report> getQuantityProducts(@PathVariable Long reportId, @PathVariable ReportPeriod period) throws Exception {
        Report valueInSales = reportService.getQuantityProducts(reportId, period);
        return ResponseEntity.ok(valueInSales);
    }
}
