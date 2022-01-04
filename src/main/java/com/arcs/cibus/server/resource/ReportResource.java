package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Report;
import com.arcs.cibus.server.service.CashService;
import com.arcs.cibus.server.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportResource {

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getAll() throws Exception {
        List<Report> reports = reportService.getAll();
        return ResponseEntity.ok(reports);
    }

    @RequestMapping(value = "/valueInSales", method = RequestMethod.GET)
    public ResponseEntity<Report> getValueInSales() throws Exception {
        return ResponseEntity.ok(new Report());
    }
}
