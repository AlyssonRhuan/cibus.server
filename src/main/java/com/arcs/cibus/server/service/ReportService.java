package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Report;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.repository.CashRepository;
import com.arcs.cibus.server.repository.ReportRepository;
import com.arcs.cibus.server.service.exceptions.DataException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ReportService
{
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAll() throws Exception
    {
        return reportRepository.findAll();
    }
}
