package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.service.CashService;
import com.arcs.cibus.server.service.ProductService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/cash")
public class CashResource {

    @Autowired
    private CashService cashService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Cash>> getAll(int page, int quantity, String user, String description, String openDate, String closeDate) throws Exception {
        Page<Cash> cashs = cashService.getAll(page - 1, quantity, user, description, openDate, closeDate);
        return ResponseEntity.ok(cashs);
    }

    @RequestMapping(value = "/{cashId}", method = RequestMethod.GET)
    public ResponseEntity<Cash> getById(@PathVariable Long cashId) throws ObjectNotFoundException {
        Cash cash = cashService.getById(cashId);
        return ResponseEntity.ok(cash);
    }

    @RequestMapping(value = "/{cashId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable Long cashId) throws ConstraintViolationException, Exception {
        cashService.delete(cashId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @RequestMapping(value = "/{cashId}", method = RequestMethod.PUT)
    public ResponseEntity<Cash> update(@PathVariable Long cashId, @RequestBody Cash cash) throws Exception {
        cash.setId(cashId);
        cash = cashService.save(cash);
        return ResponseEntity.ok(cash);
    }

    @RequestMapping(value = "/{cashId}", method = RequestMethod.PUT)
    public ResponseEntity<Cash> closeCash(@PathVariable Long cashId) throws Exception {
        Cash cash = cashService.getById(cashId);
        cash.setCloseDate(new Date());
        return ResponseEntity.ok(cash);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Cash> insert(@RequestBody Cash cash) throws Exception {
        cash.setId(null);
        cash.setOpenDate(new Date());
        cash.setCloseDate(null);
        cash.setCurrentValue(cash.getStartValue());
        cash = cashService.save(cash);
        return ResponseEntity.ok(cash);
    }
}
